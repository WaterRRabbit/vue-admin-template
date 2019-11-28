import defaultSettings from '@/settings'

const { showSettings, fixedHeader, sidebarLogo } = defaultSettings

const state = {
  showSettings: showSettings,
  fixedHeader: fixedHeader,
  sidebarLogo: sidebarLogo
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  },
  CHANGE_FIXED_HEADER: (state) => {
    state.fixedHeader = !state.fixedHeader
  }
}

const actions = {
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  },
  changeFixedHeader({ commit }) {
    commit('CHANGE_FIXED_HEADER')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

