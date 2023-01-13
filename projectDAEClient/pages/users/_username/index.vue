<template>
  <div>
    <b-container>
      <h4>User Details:</h4>
      <b-img :src="user.avatar" fluid></b-img>
      <b-form-group label="Name:">
        <b-form-input
          :value="user.name"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Email:">
        <b-form-input
          :value="user.email"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Address:" v-show="user.user_type==='Client' || user.user_type==='Insurance'">
        <b-form-input
          :value="user.address"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group v-show="user.user_type==='Client'" :label="user.nif ? 'Nif:' : 'Nipc'">
        <b-form-input
          :value="user.nif ? user.nif : user.nipc"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group v-show="user.user_type==='Insurance'" label="Share Capital">
        <b-form-input
          :value="user.share_capital"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Phone:">
        <b-form-input
          :value="user.phone"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Insurance:">
        <b-form-input
          :value="user.insuranceName"
          disabled
        ></b-form-input>
      </b-form-group>
      <b-form-group v-show="user.user_type==='Client'">
        <h4>Policies:</h4>
        <b-table v-if="policies.length"
                 striped over :items="policies" :fields="policiesFields">
          <template v-slot:cell(options)="row">
          </template>
        </b-table>
        <p v-else>No policies for this user.</p>
      </b-form-group>
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
      policiesFields: ['type', 'description'],
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
      .then(() => this.$axios.$get(`api/clients/${this.userID}/policies`))
      .then((policies) => (this.policies = policies || {}))
  },
}
</script>
<style>
h4 {
  margin-top: 5%;
}
</style>
