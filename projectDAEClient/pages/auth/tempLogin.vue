<template>
    <div>
        <b-container class="text-center">
          <h2>Index page, used for user picking</h2>
        </b-container>
        <div class="w-50 p-3 mb-1 3 mx-auto">
            <b-form @submit.prevent="userPick" class="text-left">
                <b-form-group label="User type:">
                    <b-form-select v-model="choosenUserId">
                        <option :value="null" disabled>- Choose an user</option>
                        <template v-for="user in users">
                            <option :key="user.user_id" :value="user.user_id">
                                [{{ user.user_type }}] - {{ user.name }} (id: {{ user.user_id }})
                            </option>
                        </template>
                    </b-form-select>
                </b-form-group>
                <div class="text-center">
                    <b-button type="submit" @click.prevent="userRemnove">"LOGOUT" USER</b-button>
                    <b-button variant="primary" type="submit" @click.prevent="userPick">SET USER</b-button>
                </div>
            </b-form>
        </div>
    </div>
  </template>
  
  <script>
  export default {
      data() {
          return {
            choosenUserId: null,
            users: []
          }
      },
      created() {
          this.$axios.$get(`/api/users`)
              .then(users => this.users = users)        
      },
      methods: {
          userPick() {
            localStorage.setItem("userId", this.choosenUserId)
            location.reload()
          },
          userRemnove() {
            localStorage.removeItem("userId")
            location.reload()
          }
      }
  }
  </script>