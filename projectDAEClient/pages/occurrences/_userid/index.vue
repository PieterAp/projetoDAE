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

      <nuxt-link to="/occurrences">Back</nuxt-link>
    </b-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      occurrence: {},
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
  },
}
</script>
<style>
h4 {
  margin-top: 5%;
}
</style>
