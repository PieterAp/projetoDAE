<template>
<div class="container">
  <br>
  <section class="wrapper">
    <div class="container-fostrap">
        <div class="content">
          <h2>Welcome to your favorite insurance manager</h2>
          <br>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-6">
                        <div class="card">
                            <a class="img-card">
                            <img src="~assets/images/family-insurance-banner-giant-hand-holding-vector-27257347.jpg" />
                          </a>
                            <div class="card-content">
                                <h4 class="card-title" style="text-align: center;">
                                      Policies
                                </h4>
                            </div>
                            <div class="card-read-more" style="width: 100%;">
                              <nuxt-link class="btn btn-link btn-block" to="/policies">
                                View policies
                              </nuxt-link>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <div class="card">
                            <a class="img-card" >
                            <img src="~assets/images/family-insurance-serves-happy-vector-29766060.jpg" />
                          </a>
                            <div class="card-content">
                                <h4 class="card-title" style="text-align: center;">
                                      Occurrances
                                </h4>
                            </div>
                            <div class="card-read-more">
                              <nuxt-link class="btn btn-link" to="/occurrences" style="width: 50%; float: left;">
                                View occurrances
                              </nuxt-link>
                              <nuxt-link class="btn btn-link" :to="`/occurrences/${this.$auth.user.user_id}/create`" style="width: 50%; float: initial;">
                                Create occurrance
                              </nuxt-link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</div>
</template>

<script>
export default {
  data() {
    return {
      user: {},
      userId: null
    }
  },
  created() {
    if (this.$auth.user.user_type != 'Client') {
      this.$router.push('/occurrences')
    }
  },
  mounted() {
    this.userId = this.$auth.user.user_id
    this.$axios.$get(`/api/users/${this.userId}`)
      .then((user) => {
        this.user = user
      })
  }
}
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Roboto:400,100,900);

html,
body {
  -moz-box-sizing: border-box;
       box-sizing: border-box;
  height: 100%;
  width: 100%; 
  background: #FFF;
  font-family: 'Roboto', sans-serif;
  font-weight: 400;
}
 
.wrapper {
  display: table;
  height: 100%;
  width: 100%;
}

.container-fostrap {
  display: table-cell;
  padding: 1em;
  text-align: center;
  vertical-align: middle;
}
.fostrap-logo {
  width: 100px;
  margin-bottom:15px
}
h1.heading {
  color: #fff;
  font-size: 1.15em;
  font-weight: 900;
  margin: 0 0 0.5em;
  color: #505050;
}
@media (min-width: 450px) {
  h1.heading {
    font-size: 3.55em;
  }
}
@media (min-width: 760px) {
  h1.heading {
    font-size: 3.05em;
  }
}
@media (min-width: 900px) {
  h1.heading {
    font-size: 3.25em;
    margin: 0 0 0.3em;
  }
} 
.card {
  display: block; 
    margin-bottom: 20px;
    line-height: 1.42857143;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12); 
    transition: box-shadow .25s; 
}
.card:hover {
  box-shadow: 0 8px 17px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
}
.img-card {
  width: 100%;
  height:200px;
  border-top-left-radius:2px;
  border-top-right-radius:2px;
  display:block;
    overflow: hidden;
}
.img-card img{
  width: 50%;
  height: 200px;
  object-fit:cover; 
  transition: all .25s ease;
} 
.card-content {
  padding:15px;
  text-align:left;
}
.card-title {
  margin-top:0px;
  font-weight: 700;
  font-size: 1.65em;
}
.card-title a {
  color: #000;
  text-decoration: none !important;
}
.card-read-more {
  border-top: 1px solid #D4D4D4;
}
.card-read-more a {
  text-decoration: none !important;
  padding:10px;
  font-weight:600;
  text-transform: uppercase
}
</style>