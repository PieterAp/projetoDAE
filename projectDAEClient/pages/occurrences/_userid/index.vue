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

      <b-card class="blink_me " v-show="occurrence.status === 'Approved' && this.$auth.user.user_type === 'Client'">
        <h4 style="margin-top: 0%;">Repair entity to handle this occurance</h4>

        <div class="acordion" role="tablist">
          <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel">
            Please select one of the presented repair entities down bellow to handle your occurrance,
            if none of the presented repair entities is of your interest, click <a block v-b-toggle.accordion-2
              style="cursor: pointer;">here</a>
            <br><br>

            <b-form-group label="Repair entity:">
              <b-form-select v-model="policy_id" :state="isPolicyChoiceValid" required>
                <option :value="null" disabled>Pick one of your policies</option>
                <template v-for="policy in policies">
                  <option :key="policy.id" :value="policy.id" required>
                    [{{ policy.type }}] {{ policy.description }}
                  </option>
                </template>
              </b-form-select>
              <br><br>
              <div class="text-right">
                <b-button variant="primary" type="submit" @click.prevent="create"
                  :disabled="!isFormValid">Assign</b-button>
              </div>
            </b-form-group>
          </b-collapse>

          <b-collapse id="accordion-2" accordion="my-accordion" role="tabpanel">
            Fill in this form with the contact information of the repair entity of your choice
            <br><br>
            <b-form @submit.prevent="create" class="text-left">
              <b-form-group label="Name:">
                <b-form-input v-model="name" placeholder="Enter name" required></b-form-input>
              </b-form-group>
              <b-form-group label="Email:">
                <b-form-input type="email" v-model="email" placeholder="Enter email" required></b-form-input>
              </b-form-group>
              <b-form-group label="Phone number:">
                <b-form-input v-model="name" placeholder="Enter phone number" required></b-form-input>
              </b-form-group>
              <p v-show="errorMsg" class="text-danger">{{ errorMsg }}</p>
              <div class="text-center">
                <b-button variant="warning" v-b-toggle.accordion-1>Show previous selection</b-button>
                <b-button variant="primary" type="submit" @click.prevent="create">Assign</b-button>
              </div>
            </b-form>
          </b-collapse>
        </div>

      </b-card>

      <b-form-group
        v-if="occurrence.status == 'Approved' || (occurrence.status == 'Submitted' && this.$auth.user.user_type === 'Client')">
        <h4 style="margin-top: 3%;">Upload File:</h4>
        <form @submit.prevent="upload(row)" ref="clear">
          <!-- Styled -->
          <b-form-file v-model="file" />
          <b-button :disabled="!hasFile" type="submit">Upload</b-button>
        </form>
      </b-form-group>
      <h4>Documents:</h4>
      <b-table v-if="documents.length" striped over :items="documents" :fields="documentsFields">
        <template v-slot:cell(options)="row">
          <a class="btn btn-link" @click="download(row)">Download</a>
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
      this.$axios.$get(`/api/documents/${row.item.id}/download`, { responseType: 'arraybuffer' })
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

.card-body {
  padding: 10px;
}
</style>
