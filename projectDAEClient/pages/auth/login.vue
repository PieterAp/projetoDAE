<template>
  <b-container>
    <h3>Login</h3>
    <b-form @submit.prevent="onSubmit" @reset="onReset">
      <b-form-group label="Email">
        <b-input
          name="email"
          placeholder="Your email"
          v-model.trim="email"
          required/>
      </b-form-group>
      <b-form-group label="Password">
        <b-input
          name="password"
          type="password"
          placeholder="Your password"
          v-model="password"
          required/>
      </b-form-group>
      <!--<b-button type="reset" class="btn-warning">Reset</b-button>-->
      <b-button type="submit" class="btn-success">Login</b-button>
    </b-form>
  </b-container>
</template>

<script>
export default {
  auth: false,
  data() {
    return {
      email: null,
      password: null
    }
  },
  methods: {
    onSubmit() {
      let promise = this.$auth.loginWith('local', {
        data: {
          email: this.email,
          password: this.password
        }
      })

      promise.then(() => {
        this.$toast.success('You are logged in!').goAway(3000)
        // check if the user $auth.user object is set
        this.$router.push('/')
      })
      promise.catch(() => {
        this.$toast.error("Sorry, you can't login. Ensure your credentials are correct").goAway(3000)
      })
    },
    onReset() {
      this.email = null
      this.password = null
    }
  }
}
</script>
