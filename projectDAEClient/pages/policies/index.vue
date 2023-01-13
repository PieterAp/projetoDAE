<template>
  <div>
    <b-container>
      <h4>Policies:</h4>
      <b-table v-if="policies.length" striped over :items="policies" :fields="policiesFields">
        <template v-slot:cell(actions)="row">
          <nuxt-link
            class="btn btn-link"
            :to="`/policies/${row.item.id}`">Details
          </nuxt-link>
        </template>
      </b-table>
      <p v-else>No policies for this user.</p>
      <nuxt-link to="/">Back</nuxt-link>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      policiesFields: ['type', 'description', 'actions'],
      policies: [],
    }
  },
  created() {
    this.$axios.$get(`api/clients/${this.$auth.user.user_id}/policies`)
      .then((policies) => (this.policies = policies || {}))
  },
}
</script>
