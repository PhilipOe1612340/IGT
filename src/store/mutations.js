export default {
  setUser(state, user) {
    console.log("set to user " + user.customerId);
    state.user = user;
  },
  allUsers(state, users) {
    state.allUsers = users;
  },
  setPort(state, port) {
    state.Port = port;
  },
  inboundFlight(state, flights) {
    state.inboundFlight = flights;
  },
  returnFlight(state, flights) {
    state.returnFlight = flights;
  },
  bookings(state, book) {
    state.bookings = book;
  },
  allAirports(state, ports) {
    state.airports = ports;
  },

};
