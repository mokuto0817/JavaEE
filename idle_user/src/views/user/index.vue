<template>
  <div class="app-container">
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="temp.name" disabled placeholder="姓名" />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="temp.username" disabled placeholder="用户名" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="temp.password" placeholder="密码" />
      </el-form-item>
      <el-form-item label="电话" prop="tel">
        <el-input v-model="temp.tel" disabled placeholder="电话" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">
        Cancel
      </el-button>
      <el-button type="primary" @click="updateData()">
        Confirm
      </el-button>
    </div>
  </div>
</template>

<script>
import * as user from '@/api/user'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'User',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      total: 0,
      listQuery: {
        current: 1,
        size: 10,
        name: undefined,
        username: undefined,
        tel: undefined,
      },
      roles: ['ROLE_USER', 'ROLE_ADMIN'],
      dialogPluginVisible: false,
      pluginData: [],
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      rules: {
        name: [{ required: true, message: 'name is required', trigger: 'blur' }],
        username: [{ required: true, message: 'username is required', trigger: 'blur' }],
        password: [{ required: true, message: 'password is required', trigger: 'blur' }],
        tel: [{ required: true, message: 'tel is required', trigger: 'blur' }],
      },
      temp: {
        id: undefined,
        name: '',
        username: '',
        password: '',
        tel: '',
      },
      resetTemp() {
        this.temp = this.$options.data().temp
      }
    }
  },
  created() {
    var user = JSON.parse(localStorage.getItem('user'));
    this.temp = user
  },
  methods: {
    fetchData() {
      this.listLoading = true
      user.getList(this.listQuery).then(res => {
        this.total = res.data.total
        this.list = res.data.records
        this.listLoading = false
      })
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          user.createUser(this.temp).then(() => {
            this.fetchData()
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      console.log(this.dialogStatus);
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          user.updateUser(tempData).then(() => {
            this.fetchData()
            this.dialogFormVisible = false
            localStorage.setItem('user', JSON.stringify(this.temp))
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row) {
      user.deleteUser(row.id).then(response => {
        this.fetchData()
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
    }
  }
}
</script>
