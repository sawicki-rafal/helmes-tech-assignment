export default {
  namespaced: true,
  state: {
    errorPool: [],
  },
  mutations: {
    addError(state, error) {
      state.errorPool.push(error);
    },
    discardLastError(state) {
      state.errorPool.pop();
    }
  },
  actions: {
    addError({ commit }, error) {
      commit('addError', error);
    },
    discardLastError({ commit }) {
      commit('discardLastError');
    }
  },
  getters: {
    getError(state) {
      return state.errorPool[state.errorPool.length - 1];
    }
  }
};
