<template>
  <div>
    <b-container>
      <h4>Occurrences Details:</h4>
      <b-form-group label="Description:">
        <b-form-input :value="occurrence.description" disabled></b-form-input>
      </b-form-group>
      <b-form-group label="Status:">
        <b-form-input :value="occurrence.status" disabled></b-form-input>
      </b-form-group>
      <b-form-group label="Client:">
        <b-form-input :value="occurrence.clientName" disabled></b-form-input>
      </b-form-group>
      <b-form-group label="Insurance:">
        <b-form-input :value="occurrence.insuranceName" disabled></b-form-input>
      </b-form-group>
      <b-form-group label="Policy:" v-if="occurrence.policyDescription">
        <b-form-input :value="occurrence.policyDescription" disabled></b-form-input>
      </b-form-group>
      <b-form-group label="Expert:" v-if="occurrence.repairName">
        <b-form-input :value="occurrence.repairName" disabled></b-form-input>
      </b-form-group>
      <b-form-group label="Repair:" v-if="occurrence.expertName">
        <b-form-input :value="occurrence.expertName" disabled></b-form-input>
      </b-form-group>
      <hr class="hr"/>
      <b-form-group
        v-if="occurrence.status == 'Approved' || (occurrence.status == 'Submitted' && this.$auth.user.user_type === 'Client')">
        <h4>Upload File:</h4>
        <form @submit.prevent="upload(row)" ref="clear">
          <!-- Styled -->
          <b-form-file v-model="file"/>
          <b-button :disabled="!hasFile" type="submit">Upload</b-button>
        </form>
      </b-form-group>
      <h4>Documents:</h4>
      <b-table v-if="documents.length" striped over :items="documents" :fields="documentsFields">
        <template v-slot:cell(options)="row">
          <a class="btn btn-link" @click="download(row)">Download
          </a>
        </template>
      </b-table>
      <p v-else>No Documents for this occurence.</p>
      <nuxt-link to="/occurrences">Back</nuxt-link>
      <a v-if="this.$auth.user.user_type === 'Repair' && occurrence.status != 'Closed'">
        <b-btn class="btn-success" @click="endRepair(occurrence.occurrence_id)">End repair</b-btn>
      </a>
    </b-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      occurrence: {},
      documents: [],
      documentsFields: ['owner', 'filename', 'options'],
      file: null,
    }
  },
  computed: {
    occurrenceID() {
      return this.$route.params.userid
    },

    hasFile() {
      return this.file != null
    },
    formData() {
      let formData = new FormData()
      formData.append('client_id', this.$auth.user.user_id)
      if (this.file) {
        formData.append('file', this.file)
      }
      return formData
    }

  },
  created() {
    this.$axios.$get(`/api/occurrences/${this.occurrenceID}`)
      .then((occurrence) => (this.occurrence = occurrence || {}))
      .then(() => this.$axios.$get(`api/occurrences/${this.occurrenceID}/documents`))
      .then((documents) => (this.documents = documents || {}))
  }, methods: {
    upload() {
      if (!this.hasFile) {
        return
      }
      let promisse = this.$axios.$post(`/api/occurrences/${this.occurrenceID}/documents`, this.formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      promisse.then(() => {
        this.$toast.success('File uploaded!').goAway(3000)
      }).then(() => this.$axios.$get(`api/occurrences/${this.occurrenceID}/documents`))
        .then((documents) => (this.documents = documents || {}))
      promisse.catch(() => {
        this.$toast
          .error('Sorry, could no upload file!')
          .goAway(3000)
      })
      this.file = this.$refs.clear.reset()

    },
    download(row) {
      this.$axios.$get(`/api/documents/${row.item.id}/download`, {responseType: 'arraybuffer'})
        .then((file) => {
          const url = window.URL.createObjectURL(new Blob([file]))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download', row.item.filename)
          document.body.appendChild(link)
          link.click()
        })
    },
    endRepair(occurrenceID) {
      this.$axios.$put(`/api/occurrences/${occurrenceID}`, {
        status: "Closed"
      }).then(() => {
        this.$toast.success('Occurrence as been marked as closed!').goAway(3000)
        this.$router.push('/occurrences')
      })
    }

  }
}
</script>
<style>
h4 {
  margin-top: 5%;
}
</style>
