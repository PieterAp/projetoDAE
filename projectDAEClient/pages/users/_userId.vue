<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="/">Academics</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="/users">Users</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-container>
      <h4>User Details:</h4>
      <p>Name: {{ user.name }}</p>
      <p>Email: {{ user.email }}</p>
      <p>Address: {{ user.address }}</p>
      <p>Phone: {{ user.phone }}</p>
      <nuxt-link to="/users">Back</nuxt-link>
    </b-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: {},
    }
  },
  computed: {
    userId() {
      return this.$route.params.userId
    },
  },
  created() {
    this.$axios.$get(`https://63aa2a6d7d7edb3ae621f6af.mockapi.io/users/${this.userId}`)
      .then(user => this.user = user || {})
  },
}
</script>
<style>
h4 {
  margin-top: 5%;
}
</style>

