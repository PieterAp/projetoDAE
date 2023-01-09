<template>
  <div>
    <b-container>
      <div>
        <b-table striped over :items="occurrences" :fields="fields">
          <template v-slot:cell(actions)="row">
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
      fields: ['client_id', 'insurance_id', 'policy_id', 'description'],
      occurrences: []
    }
  },
  created() {
    this.$axios.$get('/api/occurrences/')
      .then((occurrences) => {
        this.occurrences = occurrences
      })
  }
}
</script>