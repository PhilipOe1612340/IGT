import Axios from "axios";


/* 
    Customers 
*/
export const createCustomer = async ({ dispatch, getters, commit }, userConfig) => {
    commit("setUser", (await Axios.post(getters.getURL + "/customer/new", userConfig)).data);
    dispatch("allCustomers");
};
export const deleteCustomer = async ({ dispatch, getters }, customerId) => {
    await Axios.delete(getters.getURL + "/customer/" + customerId);
    dispatch("allCustomers");
};
export const setCustomer = async ({ commit, getters }, userId) => {
    commit("setUser", (await Axios.get(getters.getURL + "/customer/" + userId)).data);
};
export const updateCustomer = async ({ commit, getters }, userConfig) => {
    Object.keys(userConfig)
        .filter(key => Array.isArray(userConfig[key]))
        .forEach(arrayKey => {
            userConfig[arrayKey] = userConfig[arrayKey].filter(entry => entry && entry !== "");
        })
    commit("setUser", (await Axios.post(getters.getURL + "/customer/" + userConfig.customerId, userConfig)).data);
};
export const allCustomers = async ({ commit, getters }) => {
    commit("allUsers", (await Axios.get(getters.getURL + `/customer/all`)).data);
};


/* 
    Airport
*/
export const allAirports = async ({ commit, getters }) => {
    commit("allAirports", (await Axios.get(getters.getURL + `/airport/all`)).data);
};
export const deleteAirport = async ({ dispatch, getters }, id) => {
    await Axios.delete(getters.getURL + `/airport/` + (id || '5'));
    dispatch("allAirports");
};


/* 
    Flights
*/
export const newFlight = async ({ getters }, data) => {
    await Axios.post(getters.getURL + `/flight/new`, data);
};
export const deleteFlight = async ({ getters }, id) => {
    await Axios.delete(getters.getURL + `/flight/` + (id || '5'));
};
export const getInboundFlight = async ({ commit, getters }, filter) => {
    commit("inboundFlight", (await Axios.post(getters.getURL + `/flight/filter`, filter)).data);
};
export const getReturnFlight = async ({ commit, getters }, filter) => {
    commit("returnFlight", (await Axios.post(getters.getURL + `/flight/filter`, filter)).data);
};
export const bookFlight = async ({ dispatch, getters }, data) => {
    await Axios.post(getters.getURL + `/booking/new`, data);
    dispatch("getBookings", getters.userId);
};
export const getBookings = async ({ commit, getters }, id) => {
    commit("bookings", (await Axios.get(getters.getURL + `/booking/customer/` + (id || '5')).data));
};
export const cancelBooking = async ({ dispatch, getters }, id) => {
    await Axios.delete(getters.getURL + `/booking/` + (id || '5'));
    dispatch("getBookings", getters.userId);
};

/* 
    administration
*/
export const clear = async ({ getters }) => {
    await Axios.get(getters.getURL + `/dropdb`);
};