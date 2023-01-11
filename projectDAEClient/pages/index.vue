<template>
  <b-container class="text-center">
    <h2>Welcome to your favorite insurance manager</h2>
    <div class="text-left" v-if="user.user_type == 'Client'">
      <br>
      <h5>As a client I want to:</h5>
      <div class="h6" style="margin-left: 20px;">
        • Create a new occurance
        <b-button variant="primary" :to="`/occurrences/${userId}/create`">Go</b-button>
        <br>
        • View created occurances, check their state
      </div>
    </div>

    <div class="text-left" v-if="user.user_type == 'Insurance'">
      <br>
      <h5>As an insurance company I want to:</h5>
      <div class="h6" style="margin-left: 20px;">
        • View occurrances
        <br>
        • View clients
      </div>
    </div>

    <div class="text-left" v-if="user.user_type == 'Expert'">
      <br>
      <h5>As an expert I want to:</h5>
      <div class="h6" style="margin-left: 20px;">
        • Manage occurances which have been assigned to me
      </div>
    </div>

    <div class="text-left" v-if="user.user_type == 'Repair'">
      <br>
      <h5>As a repair employee I want to:</h5>
      <div class="h6" style="margin-left: 20px;">
        • Manage occurances which have been assigned to me
      </div>
    </div>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      user: {},
      userId: null
    }
  },
  mounted() {
    console.log(this.$auth.user)
    this.userId = this.$auth.user.user_id
    this.$axios.$get(`/api/users/${this.userId}`)
      .then((user) => {
        this.user = user
      })
  }
}
</script>
