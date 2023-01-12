<template>
  <div>
    <b-container>
      <h4>Occurrences Details:</h4>
      <p>Description: {{ occurrence.description }}</p>
      <p>Status: {{ occurrence.status }}</p>
      <p>Client: {{ occurrence.clientName }}</p>
      <p>Insurance: {{ occurrence.insuranceName }}</p>
      <p v-show="occurrence.repairName">Repair: {{ occurrence.repairName }}</p>
      <p v-show="occurrence.expertName">Expert: {{ occurrence.expertName }}</p>
      <p v-show="occurrence.policyDescription">Policy: {{ occurrence.policyDescription }}</p>
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
