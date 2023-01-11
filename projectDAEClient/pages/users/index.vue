<template>
  <div>
    <b-container>
      <div>
        <b-table striped over :items="users" :fields="fields">
          <template v-slot:cell(actions)="row">
            <nuxt-link
              class="btn btn-link"
              :to="`/users/${row.item.user_id}`">Details
            </nuxt-link>
            <nuxt-link
              v-if="row.item.user_type=='Client'"
              class="btn btn-link"
              :to="`/occurrences/${row.item.user_id}/create`">Create occurrence
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
      fields: ['name', 'email', 'user_type', 'address', 'phone', 'actions'],
      users: []
    }
  },
  created() {
    this.$axios.$get('/api/users/')
      .then((users) => {
        this.users = users
      })
  }
}
</script>

