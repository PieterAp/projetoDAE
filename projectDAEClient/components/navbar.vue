<template>
  <b-navbar toggleable="lg" type="dark" class="navbar bg-dark navbar-dark" variant="info">
    <b-navbar-brand href="/">Academics</b-navbar-brand>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-navbar-nav>
      <b-nav-item href="/users">Users</b-nav-item>
    </b-navbar-nav>
    <b-collapse id="nav-collapse" is-nav>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" href="/auth/login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/auth/logout">Logout</a>
        </li>
      </ul>
    </b-collapse>
  </b-navbar>
</template>

