<template>
  <div>
    <b-container>
      <h4>User Details:</h4>
      <b-img :src="user.avatar" fluid></b-img>
      <p>Name: {{ user.name }}</p>
      <p>Email: {{ user.email }}</p>
      <p>Address: {{ user.address }}</p>
      <p>Phone: {{ user.phone }}</p>
      <h4>Policies:</h4>
      <b-table v-if="policies.length" 
        striped over :items="policies" :fields="policiesFields">
        <template v-slot:cell(options)="row">
        </template>
      </b-table>
      <p v-else>No policies for this user.</p>
      <nuxt-link to="/users">Back</nuxt-link>
    </b-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: {},
      policies: [],
      policiesFields: ['type','description'],
    }
  },
  computed: {
  userID() {
    return this.$route.params.username
  },
  
},
  created() {
    this.$axios.$get(`/api/users/${this.userID}`)
          .then((user) => (this.user = user || {}))
      .then(() => this.$axios.$get(`api/users/${this.userID}/policies`))
      .then((policies) => (this.policies = policies || {}))
  },
}
</script>
<style>
h4 {
  margin-top: 5%;
}
</style>