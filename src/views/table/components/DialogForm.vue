<template>
  <el-dialog
    title="Form"
    :visible.sync="dialogVisible"
    :before-close="onClose"
  >
    <div class="from-container">
      <el-form ref="form" :model="form" label-width="120px" :rules="rules">
        <el-form-item label="Username" prop="username">
          <el-input v-model="form.username" />
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="form.password" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Submit</el-button>
          <el-button @click="onCancel">Cancel</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-dialog>
</template>

<script>
import { add, update } from '@/api/user'
import Swal from 'sweetalert2'
export default {
  data() {
    return {
      form: {
        id: undefined,
        username: '',
        password: ''
      },
      formStatus: '',
      dialogVisible: false,
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.formStatus === 'add') {
            add(this.form).then(response => {
              this.$notify({
                title: '成功',
                type: 'success'
              })
              console.log(response)
              this.$refs['form'].resetFields()
              this.dialogVisible = false
            })
          } else {
            update(this.form).then(response => {
              console.log(response)
              this.$notify({
                title: '成功',
                type: 'success'
              })
              this.$refs['form'].resetFields()
              this.dialogVisible = false
            })
          }
        } else {
          return false
        }
      })
    },
    onCancel() {
      this.$refs['form'].resetFields()
      this.dialogVisible = false
    },
    onOpen(row) {
      this.dialogVisible = true
      if (row) {
        this.formStatus = 'update'
        this.form.username = row.username
        this.form.password = row.password
      } else {
        this.formStatus = 'add'
        this.$refs['form'].resetFields()
      }
    },
    onClose(done) {
      this.$refs['form'].resetFields()
      this.dialogVisible = false
      done()
    }
  }
}
</script>
