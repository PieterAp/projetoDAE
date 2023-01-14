<template>
  <div>
    <b-container>
      <div>
        <b-table striped over :items="occurrences" :fields="fields">
          <template v-slot:cell(actions)="row">
            <nuxt-link
              class="btn btn-link"
              :to="`/occurrences/${row.item.occurrence_id}`">Details
            </nuxt-link>
            <div v-if="row.item.status=='Submitted'">
              <a v-show="user.user_type === 'Expert'">
                <b-btn class="btn-success" @click="approveOccurrence(row)">Approve</b-btn>
                <b-btn class="btn-danger" @click="disapproveOccurrence(row)">Disapprove</b-btn>
              </a>
            </div>
          </template>
        </b-table>
      </div>
    </b-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      fields: ['clientName', 'insuranceName', 'policyName', 'expertName', 'repairName', 'description', 'status', 'actions'],
      occurrences: [],
      user: false,
      file: null,
    }
  },
  created() {
    this.user = this.$auth.user
    this.$axios.$get('/api/users/' + this.user.user_id + "/occurrences")
      .then((occurrences) => {
        this.occurrences = occurrences
      })
  },
  computed: {},
  methods: {
    approveOccurrence(row) {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Approved",
        expert_id: this.user.user_id
      }).then(() => this.$axios.$get('/api/users/' + this.user.user_id + "/occurrences"))
        .then((occurrences) => (this.occurrences = occurrences))
    },
    disapproveOccurrence(row) {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Disapproved",
        expert_id: this.user.user_id
      }).then(() => this.$axios.$get('/api/users/' + this.user.user_id + "/occurrences"))
        .then((occurrences) => (this.occurrences = occurrences))
    },
    endRepair(row) {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Repaired"
      }).then(() => {
        location.reload();
      })
    },
  }
}
</script>
