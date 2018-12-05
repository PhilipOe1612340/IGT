<template>
  <v-app>
    <div id="app">
      <div id="nav">
        <v-toolbar id="toolbar" color="blue-grey darken-3">
          <v-toolbar-title>Flightmanagement</v-toolbar-title>
          <v-spacer></v-spacer>

          <v-menu :nudge-width="100">
            <v-toolbar-title slot="activator">
              <span>{{ dbSelected }}</span>
              <v-icon id="dropIcon">arrow_drop_down</v-icon>
            </v-toolbar-title>

            <v-list>
              <v-list-tile v-for="item in items" :key="item.port" @click="setPort(item)">
                <v-list-tile-title v-text="item.name"></v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-menu>
        </v-toolbar>
      </div>

      <router-view/>
      <div id="spacer"></div>

      <v-footer height="auto" id="footer">
        <v-layout justify-center row wrap>
          <v-btn color="gray" flat round to="/flights">Flüge</v-btn>
          <v-btn color="gray" flat round to="/user">User</v-btn>
          <v-flex py-3 text-xs-center xs12>
            &copy;2018 —
            <strong>Raphi + Philip</strong>
          </v-flex>
        </v-layout>
      </v-footer>
    </div>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    dbSelected: "MySQL",
    items: [
      { name: "MySQL", port: 6001 },
      { name: "MongoDB", port: 6002 },
      { name: "Ehcache", port: 6003 },
      { name: "Neo4j", port: 6004 },
      { name: "Redis", port: 6005 }
    ]
  }),
  mounted() {
    const stored = window.localStorage.getItem("db");
    if (stored) {
      this.setPort(this.items.find(i => i.name === stored));
    } else {
      this.$store.dispatch("allCustomers");
    }
    this.$store.dispatch("allAirports");
  },
  methods: {
    setPort(item) {
      this.dbSelected = item.name;
      window.localStorage.setItem("db", this.dbSelected);
      this.$store.commit("allUsers", []);
      this.$store.commit("setPort", item.port);
      this.$store.dispatch("allCustomers");
    }
  }
};
</script>

<style lang="scss" scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#footer {
  color: darkgray !important;
  position: absolute;
  bottom: 0;
  height: 40px;
  margin-top: 40px;
  width: 100%;
}
#toolbar,
#dropIcon {
  color: white;
}
#spacer {
  margin-bottom: 110px;
}
</style>
