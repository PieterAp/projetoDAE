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
            <a v-if="row.item.status=='Submitted'">
              <a v-show="user.user_type === 'Expert'">
                <b-btn class="btn-success" @click="approveOccurrence(row)">Approve</b-btn>
                <b-btn class="btn-danger" @click="disapproveOccurrence(row)">Disapprove</b-btn>
              </a>
              <a v-show="user.user_type === 'Insurance'">
                <b-btn class="btn-success" @click="acceptOccurrence(row)">Accept</b-btn>
                <b-btn class="btn-danger" @click="denyOccurrence(row)">Deny</b-btn>
              </a>
            </a>
            <a v-if="row.item.status=='Approved'">
              <a v-show="user.user_type === 'Repair' || user.user_type === 'Expert'">
                <b-btn class="btn-information" @click="uploadFile(row)">Upload repair files</b-btn>
              </a>
              <a v-show="user.user_type === 'Repair'">
                <b-btn class="btn-success" @click="endRepair(row)">End repair</b-btn>
              </a>
            </a>
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
  computed: {

    formData() {
      let formData = new FormData()
      formData.append('id', this.$auth.user.user_id)
      if (this.file) {
        formData.append('file', this.file)
      }
      return formData
    }
  },
  methods: {
    approveOccurrence(row) {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Approved"
      }).then(() => {
        location.reload();
      })
    },
    disapproveOccurrence(row) {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Disapproved"
      }).then(() => {
        location.reload();
      })
    },
    //TODO accept/deny
    acceptOccurrence(row)  {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Approved"
      }).then(() => {
        location.reload();
      })
    },
    denyOccurrence(row)  {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Disapproved"
      }).then(() => {
        location.reload();
      })
    },
    endRepair(row) {
      this.$axios.$put(`/api/occurrences/${row.item.occurrence_id}`, {
        status: "Repaired"
      }).then(() => {
        location.reload();
      })
    },

    onPickFile() {
      
      this.$refs.fileInput.click()
    },

    onFilePicked(row) {
      this.file = event.target.files[0]
      let promisse = this.$axios.$post(`/api/occurrences/${row.item.occurrence_id}/documents`, this.formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      promisse.then(() => {
        this.$toast.success('File uploaded!').goAway(3000)
      })
      promisse.catch(() => {
        this.$toast
          .error('Sorry, could no upload file!')
          .goAway(3000)
      })
    },
  }
}
</script>
