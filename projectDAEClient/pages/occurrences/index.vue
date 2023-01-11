<template>
  <div>
    <b-container>
      <div>
        <b-table striped over :items="occurrences" :fields="fields">
          <template v-slot:cell(actions)="row">
            <div v-if="row.item.status=='Submitted'">
              <b-btn class="btn-success" @click="approveOccurrence(row)">Approve</b-btn>
              <b-btn class="btn-danger" @click="disapproveOccurrence(row)">Disapprove</b-btn>
            </div>
            <div v-if="row.item.status=='Approved'">
              <b-btn class="btn-information" @click="uploadFile(row)">Upload repair files</b-btn>
              <b-btn class="btn-success" @click="endRepair(row)">End repair</b-btn>
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
      fields: ['client_id', 'insurance_id', 'policy_id', 'description', 'status', 'actions'],
      occurrences: []
    }
  },
  created() {
    this.$axios.$get('/api/occurrences/')
      .then((occurrences) => {
        this.occurrences = occurrences
      })
  },
  methods: {
    approveOccurrence(row){
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
                status: "Approved"
      }).then(() => {
        location.reload();
      })
    },
    disapproveOccurrence(row){
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
                status: "Disapproved"
      }).then(() => {
        location.reload();
      })
    },
    endRepair(row){
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
                status: "Repaired"
      }).then(() => {
        location.reload();
      })
    }
  }
}
</script>