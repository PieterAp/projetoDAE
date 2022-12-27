<template>
  <div>
    <navbar>
    </navbar>
    <b-container>
      <div>
        <b-table striped over :items="users" :fields="fields">
          <template v-slot:cell(actions)="row">
            <nuxt-link
              class="btn btn-link"
              :to="`/users/${row.item.user_id}`">Details
            </nuxt-link>
          </template>
        </b-table>
      </div>
    </b-container>
  </div>
</template>


<script>
export default {
  data() {
    return {
      fields: ['user_id','name', 'email', 'actions'],
      users: []
    }
  },
  created() {
    this.$axios.$get('https://63aa2a6d7d7edb3ae621f6af.mockapi.io/api/users/')
      .then((users) => {
        this.users = users
      })
  }
}
</script>

