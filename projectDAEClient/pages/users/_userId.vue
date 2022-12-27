<template>
  <div>
    <navbar>
    </navbar>
    <b-container>
      <h4>User Details:</h4>
      <b-img :src="user.avatar" fluid></b-img>
      <p>Name: {{ user.name }}</p>
      <p>Email: {{ user.email }}</p>
      <p>Address: {{ user.address }}</p>
      <p>Phone: {{ user.phone }}</p>
      <h4>Policys:</h4>
      <b-table v-if="policys.length" striped over :items="policys" :fields="policysFields">
        <template v-slot:cell(options)="row">
        </template>
      </b-table>
      <p v-else>No policys for this user.</p>
      <nuxt-link to="/users">Back</nuxt-link>
    </b-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: {},
      policys: [],
      policysFields: ['policy_type','description'],
    }
  },
  computed: {
    userId() {
      return this.$route.params.userId
    },
  },
  created() {
    this.$axios.$get(`https://63aa2a6d7d7edb3ae621f6af.mockapi.io/api/users/${this.userId}`)
      .then(user => this.user = user || {})
      .then(() => this.$axios.$get(`https://63aa2a6d7d7edb3ae621f6af.mockapi.io/api/users/${this.userId}/policys`))
      .then(policys => this.policys = policys)
  },
}
</script>
<style>
h4 {
  margin-top: 5%;
}
</style>

