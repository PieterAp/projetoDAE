<template>
  <div>
    <b-container>
      <div>
        <b-table striped over :items="occurrences" :fields="fields">
          <template v-slot:cell(actions)="row">
            <div v-if="row.item.status == 'Submitted'">
              <b-btn class="btn-success" @click="approveOccurrence(row)">Approve</b-btn>
              <b-btn class="btn-danger" @click="disapproveOccurrence(row)">Disapprove</b-btn>
            </div>
            <div v-if="row.item.status == 'Approved'">


              <b-btn class="btn-information" @click="onPickFile">Upload repair files</b-btn>
              <input type="file" style="display: none" ref="fileInput" @change="onFilePicked(row)" />



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
      occurrences: [],
      file: null,
    }
  },
  created() {
    this.$axios.$get('/api/occurrences/')
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