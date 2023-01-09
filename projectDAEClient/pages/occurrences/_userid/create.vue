<template>
    <div>
        <b-container class="text-center">
            <b-alert show class="w-75 mx-auto">Creating a new occurrence</b-alert>
        </b-container>
        <div class="w-50 p-3 mb-1 3 mx-auto">
            <b-form @submit.prevent="create" class="text-left">
                <b-form-group label="Client:">
                    <b-form-input
                    v-model="this.user.name"
                    disabled
                    ></b-form-input>
                </b-form-group> 

                <b-form-group label="Insurance company:">
                    <b-form-select v-model="insurance_id">
                        <option :value="null" disabled>- Choose an option -</option>
                        <template v-for="insuranceCompany in insuranceCompanies">
                            <option :key="insuranceCompany.user_id" :value="insuranceCompany.user_id">
                                {{ insuranceCompany.name }}
                            </option>
                        </template>
                    </b-form-select>
                </b-form-group>
                
                <b-form-group label="Policy:">
                    <b-form-select v-model="policy_id">
                        <option :value="null" disabled>- Choose an option -</option>
                        <template v-for="policy in policies">
                            <option :key="policy.id" :value="policy.id">
                                {{ policy.description }}
                            </option>
                        </template>
                    </b-form-select>
                </b-form-group>

                <b-form-group label="Description:">
                    <b-form-input
                    v-model="description"
                    placeholder="Enter description"
                    required
                    ></b-form-input>
                </b-form-group>

                <p v-show="errorMsg" class="text-danger">{{errorMsg}}</p>
                <div class="text-center">
                    <nuxt-link to="/users">Return</nuxt-link>
                    <b-button variant="danger" type="reset" @click="errorMsg=false">RESET</b-button>
                    <b-button variant="primary" type="submit" @click.prevent="create">CREATE</b-button>
                </div>
            </b-form>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            description: null,
            insurance_id: null,
            policy_id: null,
            user: [],
            policies: {},
            insuranceCompanies: [],
            errorMsg: false
        }
    },
    computed: {
        userid() {
            return this.$route.params.userid
        }
    },
    created() {
        this.$axios.$get(`/api/users/${this.userid}`)
            .then(user => this.user = user)
            .then(() => this.$axios.$get(`/api/users/${this.userid}/policies`))
            .then(policies => this.policies = policies)        
            .then(() => this.$axios.$get(`/api/users/insuranceComp`))
            .then(insuranceCompanies => this.insuranceCompanies = insuranceCompanies)        
        
    },
    methods: {
        create() {
            this.$axios.$post('/api/occurrences/', {
                client_id: this.user.user_id,
                insurance_id: this.insurance_id,
                policy_id: this.policy_id,
                description: this.description,
                status: "Submitted"
            })
            .then(() => {
                this.$router.push('/occurrences')
            })
            .catch((error) => {
                this.errorMsg = error.response.data
            })
        },
    }
}
</script>