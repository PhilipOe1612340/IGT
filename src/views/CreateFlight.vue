<template>
  <div>
    <v-speed-dial bottom right>
      <v-btn slot="activator" @click="dialog = true" dark fab>
        <v-icon>flight_takeoff</v-icon>
      </v-btn>
    </v-speed-dial>

    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Create a new Flight</v-card-title>
        <v-card-text>
          <v-layout row wrap>
            <v-flex xs12 sm6 pr-2>
              <v-autocomplete
                class="airport"
                v-model="start"
                :items="$store.getters.airports"
                item-text="name"
                label="Select start"
                no-data-text="Airport not found"
                persistent-hint
              />
            </v-flex>
            <v-flex xs12 sm6 pl-2>
              <v-autocomplete
                class="airport"
                v-model="goal"
                :items="$store.getters.airports"
                item-text="name"
                label="Select goal"
                no-data-text="Airport not found"
                persistent-hint
              />
            </v-flex>
            <v-flex xs12 sm6 md6 pr-2>
              <v-menu
                v-model="menuDate"
                :return-value.sync="date"
                lazy
                transition="scale-transition"
                offset-y
                full-width
                min-width="290px"
              >
                <v-text-field
                  slot="activator"
                  v-model="date"
                  label="Date"
                  prepend-icon="event"
                  readonly
                ></v-text-field>
                <v-date-picker v-model="date" no-title scrollable></v-date-picker>
              </v-menu>
            </v-flex>
            <v-flex xs12 sm6 md6 pl-2>
              <v-menu
                v-model="menuTime"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                full-width
                min-width="290px"
              >
                <v-text-field slot="activator" v-model="time" label="Time" readonly></v-text-field>
                <v-time-picker v-model="time" format="24hr"></v-time-picker>
              </v-menu>
            </v-flex>
            <v-flex xs6 pr-2>
              <v-text-field v-model="miles" type="number" label="Miles"/>
            </v-flex>
            <v-flex xs6 pl-2>
              <v-text-field v-model="duration" type="number" label="Duration (in minutes)"/>
            </v-flex>
          </v-layout>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn flat color="orange" @click="create()">create</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    date: new Date().toISOString().substr(0, 10),
    time: null,
    menuDate: false,
    menuTime: false,
    miles: 100,
    duration: 60,
    start: null,
    goal: null
  }),
  methods: {
    create() {
      this.$store.dispatch("newFlight", {
        miles: this.miles,
        date: this.date,
        time: this.time,
        duration: this.duration,
        airportsList: [this.start.airportId, this.goal.airportId]
      });
      this.dialog = false;
    }
  },

};
</script>

<style scoped>
</style>
