import axios from 'axios'

export default {
  checkLogin(state) {
    return new Promise((resolve, reject) => {
      axios
        .get('/api/user/info', {
          params: {
            state: state
          }
        })
        .then(response => {
          if (response.data.status && response.data.errorCode == 401) {
            window.location.href = response.data.errorMessage
          } else if (response.data.status) {
            const userInfo = response.data.result
            resolve(userInfo)
          }
        })
    })

  }
}
