<template>
    <div id="app">
        <b-navbar toggleable="lg">
            <b-navbar-brand to="/">
                projetoDAE
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
                
                <b-navbar-nav class="ml-auto">
                    <li class="nav-item h6">
                        <small>I am:</small> {{ user.name }} ({{ user.user_type }}), id: {{ user.user_id }}
                    </li>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <b-nav-item-dropdown v-if="$auth.loggedIn" right>
                        <!-- Using 'button-content' slot -->
                        <template #button-content>
                            <em>{{ $auth.user.username }}</em>
                        </template>
                        <b-dropdown-item @click.prevent="signOut">
                            Sign Out
                        </b-dropdown-item>
                    </b-nav-item-dropdown>
                    <li class="nav-item" v-else>
                        <!--<nuxt-link class="nav-link" to="/auth/login">-->
                        <nuxt-link class="nav-link" to="/auth/tempLogin">
                            Sign In
                        </nuxt-link>
                    </li>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
        <main>
            <Nuxt />
        </main>
    </div>
</template>
<script>
export default {
    data() {
        return {
            user: {}
        }
    },
    mounted() {
        const userId = localStorage.getItem("userId")
        this.$axios.$get(`/api/users/${userId}`)
        .then(user => {
                this.user = user
        })
    },
    methods: {
        signOut() {
            this.$auth.logout()
            this.$router.push('/')
        }
    }
}
</script>