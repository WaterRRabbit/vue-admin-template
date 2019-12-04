<template>
  <editor :is-new="false" />
</template>

<script>
import Editor from './editor'
import Swal from 'sweetalert2'

export default {
  name: 'EditArticle',
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
      }
    })
  }
}
</script>
