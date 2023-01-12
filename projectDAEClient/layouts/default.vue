<template>
  <div id="app">
    <b-navbar toggleable="lg" class="navbar bg-dark navbar-dark">
      <b-navbar-brand to="/">
        Home
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <li class="nav-item">
            <nuxt-link class="nav-link" to="/users">
              Users
            </nuxt-link>
          </li>
          <li class="nav-item">
            <nuxt-link class="nav-link" to="/occurrences">
              Occurrences
            </nuxt-link>
          </li>
        </b-navbar-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown v-if="$auth.loggedIn" right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em style="padding-right: 15px">{{ $auth.user.name + " [" + $auth.user.user_type + "]" }}</em>
            </template>
            <b-dropdown-item @click.prevent="profile">
              Profile
            </b-dropdown-item>
            <b-dropdown-item @click.prevent="signOut">
              Sign Out
            </b-dropdown-item>
          </b-nav-item-dropdown>
          <li class="nav-item" v-else>
            <nuxt-link class="nav-link" to="/auth/login">
              Sign In
            </nuxt-link>
          </li>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <main>
      <Nuxt/>
    </main>
  </div>
</template>
<script>
export default {
  methods: {
    signOut() {
      this.$auth.logout()
      this.$router.push('/')
    },
    profile() {
      this.$router.push('/users/'+this.$auth.user.user_id)
    }
  }
}
</script>
