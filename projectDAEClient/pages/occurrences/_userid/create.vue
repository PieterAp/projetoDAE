<template>
    <div>
        <b-container class="text-center">
            <b-alert show class="w-80 mx-auto">Creating a new occurrence</b-alert>
        </b-container>
        <div class="w-50 p-3 mb-1 3 mx-auto">
            <b-form @submit.prevent="create" class="text-left">                
                <b-form-group label="Policy:">
                    <b-form-select v-model="policy_id" :state="isPolicyChoiceValid" required>
                        <option :value="null" disabled>Pick one of your policies</option>
                        <template v-for="policy in policies">
                            <option :key="policy.id" :value="policy.id" required>
                                [{{policy.type}}] {{ policy.description }}
                            </option>
                        </template>
                    </b-form-select>
                </b-form-group>

                <b-form-group label="Insurance company that will be handling this occurrance:">
                    <b-form-input
                    v-model="insuranceCompName"
                    disabled
                    ></b-form-input>
                </b-form-group>

                <b-form-group label="Description:">
                    <b-form-textarea
                    :state="isDescriptionValid"
                    required
                    v-model="description"
                    placeholder="Enter description"
                    rows="4"
                    minlength="10"
                    maxlength="255"
                    ></b-form-textarea>
                    <div class="text-right text-muted small">
                        Min 10 caracteres, max 255 caracteres
                    </div>
                </b-form-group>

                <p v-show="errorMsg" class="text-danger">{{errorMsg}}</p>
                <div class="text-center">
                    <nuxt-link to="/">Return</nuxt-link>
                    <b-button variant="primary" type="submit" @click.prevent="create" :disabled="!isFormValid">CREATE</b-button>
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
            policy_id: null,
            user: [],
            policies: [],
            insuranceCompanies: [],
            errorMsg: false
        }
    },
    computed: {
        userid() {
            return this.$route.params.userid
        },
        insuranceCompName() {
            if (this.policy_id != null) {
                let foundPolicy = this.policies.find(o => o.id === this.policy_id);
                if (foundPolicy == undefined)
                    return ""
                let foundInsurance = this.insuranceCompanies.find(o => o.user_id === foundPolicy.insurance_id);
                if (foundInsurance == undefined)
                    return ""
                return foundInsurance.name;
            }

        },
        isPolicyChoiceValid() {
            if (!this.policy_id) {
                return null
            }
            if (this.policy_id == null) {
                return false
            }
            return true
        },
        isDescriptionValid() {
            if (!this.description) {
                return null
            }
            let usernameLen = this.description.length
            if (usernameLen < 10 || usernameLen > 255) {
                return false
            }
            return true
        },
        isFormValid() {
            if (!this.isPolicyChoiceValid) {
                return false
            }
            if (!this.isDescriptionValid) {
                return false
            }
            return true
        }
    },
    created() {
        this.$axios.$get(`/api/clients/${this.userid}/policies`)
            .then(policies => this.policies = policies)
            .catch((error) => {
                this.$toast.error("An error has occured!").goAway(3000)
                this.errorMsg = error.response.data
            })
            .then(() => this.$axios.$get(`/api/insurances`))
            .then(insuranceCompanies => this.insuranceCompanies = insuranceCompanies)        
            .catch((error) => {
                this.$toast.error("An error has occured!").goAway(3000)
                this.errorMsg = error.response.data
            })
    },
    methods: {
        create() {
            if (!this.isFormValid) {
                this.$toast.error("An error has occured form fields aren't valid!").goAway(3000)
                return
            }
            this.$axios.$post('/api/occurrences/', {
                client_id: this.userid,
                policy_id: this.policy_id,
                description: this.description
            })
            .then(() => {
                this.$toast.success('Occurrance was created!').goAway(3000)
                this.$router.push('/occurrences')
            })
            .catch((error) => {
                this.$toast.error("An error has occured!").goAway(3000)
                this.errorMsg = error.response.data
            })
        }
    }
}
</script>