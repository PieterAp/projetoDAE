<template>
  <div>
    <b-container><h4>Occurrences Details:</h4>
      <b-form-group label="Description:">
        <b-form-input
          :value="occurrence.description"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Status:">
        <b-form-input
          :value="occurrence.status"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Client:">
        <b-form-input
          :value="occurrence.clientName"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Insurance:">
        <b-form-input
          :value="occurrence.insuranceName"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Policy:" v-show="occurrence.policyDescription">
        <b-form-input
          :value="occurrence.policyDescription"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Expert:" v-show="occurrence.repairName">
        <b-form-input
          :value="occurrence.repairName"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Repair:" v-show="occurrence.expertName">
        <b-form-input
          :value="occurrence.expertName"
          disabled
        ></b-form-input>
      </b-form-group>
      <h4>Documents:</h4>
      <b-table v-if="documents.length"
               striped over :items="documents" :fields="documentsFields">
        <template v-slot:cell(options)="row">
          <a
            class="btn btn-link"
            @click="download(row)">Download
          </a>
        </template>
      </b-table>
      <p v-else>No Documents for this occurence.</p>
      <nuxt-link to="/occurrences">Back</nuxt-link>
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
    }
  },
  computed: {
    occurrenceID() {
      return this.$route.params.userid
    },

  },
  created() {
    this.$axios.$get(`/api/occurrences/${this.occurrenceID}`)
      .then((occurrence) => (this.occurrence = occurrence || {}))
      .then(() => this.$axios.$get(`api/occurrences/${this.occurrenceID}/documents`))
      .then((documents) => (this.documents = documents || {}))
  }, methods: {
    download(row) {
      this.$axios.$get(`/api/documents/${row.item.id}/download`, { responseType: 'arraybuffer'})
        .then((file) => {
          const url = window.URL.createObjectURL(new Blob([file]))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download', row.item.filename)
          document.body.appendChild(link)
          link.click()
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
