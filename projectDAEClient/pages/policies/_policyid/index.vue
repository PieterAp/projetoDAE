<template>
  <div>
    <b-container>
      <h4>Policy Details:</h4>
      <b-form-group label="Type:">
        <b-form-input
          :value="policy.type"
          disabled
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Description:">
        <b-form-input
          :value="policy.description"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Insurance:">
        <b-form-input
          :value="insuranceName.name"
          disabled
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Data:">
        <b-form-input
          :value="this.$moment(String(policy.createdAt)).format('MM/DD/YYYY hh:mm')"
          disabled
        ></b-form-input>
      </b-form-group>

      <h4>Occurrencies:</h4>
      <b-table striped over :items="occurrences" :fields="fields">
        <template v-slot:cell(actions)="row">
          <nuxt-link
            class="btn btn-link"
            :to="`/occurrences/${row.item.occurrence_id}`">Details
          </nuxt-link>
        </template>
      </b-table>
      <nuxt-link to="/policies">Back</nuxt-link>
    </b-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      policy: {},
      occurrences: [],
      insuranceName: "",
      fields: [ 'description', 'status','actions'],
    }
  },
  computed: {
    policyid() {
      return this.$route.params.policyid
    },

  },
  created() {
    this.$axios.$get(`/api/policies/${this.policyid}`)
      .then((policy) => (this.policy = policy))
      .then(() => this.$axios.$get(`/api/users/${this.policy.insurance_id}`))
      .then((insuranceName) => (this.insuranceName = insuranceName || {}))
      .then(() => this.$axios.$get(`/api/policies/${this.policy.id}/occurrences`))
      .then((occurrences) => (this.occurrences = occurrences || {}))
  },
}
</script>
<style>
h4 {
  margin-top: 5%;
}
</style>
