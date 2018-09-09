import axios from 'axios'

export default {
  checkLogin() {
    axios
      .get('/api/user/info')
      .then(response => {
        if (response.data.status && response.data.errorCode == 401) {
          window.location.href = response.data.errorMessage
        }
      })
  }
}
