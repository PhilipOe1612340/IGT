import Vue from "vue";
import Router from "vue-router";
import Flights from "./views/Flights.vue";
import Users from "./views/Users.vue";

Vue.use(Router);

export default new Router({
  routes: [
    { path: "*", redirect: "/flights" },
    {
      path: "/flights",
      name: "home",
      component: Flights
    },
    {
      path: "/user",
      name: "User",
      component: Users
    }
  ]
});
