export const getUser = state => state.user;
export const getAllUsers = state => state.allUsers;
export const getURL = state => state.portmode ? state.URL + ":" + state.Port + "/api" : state.URL;
export const userId = state => (state.user || { customerId: undefined }).customerId;
export const airports = state => state.airports;
export const bookings = state => state.bookings;
export const inboundFlight = state => state.inboundFlight;
export const returnFlight = state => state.returnFlight;