<template>
  <editor :is-new="true" />
</template>

<script>
import Editor from './editor'
import Swal from 'sweetalert2'

export default {
  name: 'CreateArticle',
  components: { Editor },
  beforeRouteLeave: function(to, from, next) {
    next(false)
    Swal.fire({
      title: 'Are you sure?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'
    }).then((result) => {
      if (result.value) {
        next()
      } else {
        // var b = document.querySelector('.\\/article\\/create li')
        // b.classList.add('is-active')
        // var c = document.querySelector('.\\/article\\/list li')
        // c.classList.remove('is-active')
      }
    })
  }
}
</script>
