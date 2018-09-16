import axios from 'axios'

export default {
  checkLogin(state) {
    axios
      .get('/api/user/info', {
        params: {
          state: state
        }
      })
      .then(response => {
        if (response.data.status && response.data.errorCode == 401) {
          window.location.href = response.data.errorMessage
        }
      })
  }
}
