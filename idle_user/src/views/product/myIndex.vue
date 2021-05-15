<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable placeholder="物品名称" style="width: 150px;" class="filter-item" />
      <el-select v-model="listQuery.label" clearable placeholder="标签" class="filter-item">
        <el-option
          v-for="item in labels"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="fetchData">
        Search
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
    </div>
    <el-row :gutter="40">
      <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6" class="m_b2" v-for="(item, index) in list"
              :key="index">
        <div class="detailsImgBox" @click="handleUpdate(item)">
          <el-image style="width: 220px; height: 220px"
                    :src="item.img"
                    fit="contain"></el-image>
        </div>
        <div class="flex_b_c m_b05">
          <div>{{item.name}}</div>
        </div>
      </el-col>
    </el-row>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="fetchData" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="名称" prop="name">
          <el-input v-model="temp.name" placeholder="名称" />
        </el-form-item>
        <el-form-item label="图片" prop="img">
          <el-upload
            class="upload-demo"
            :action="fileUpload"
            :on-preview="handlePreview"
            multiple
            :limit="1"
            :before-upload="beforeAvatarUpload"
            :on-exceed="handleExceed"
            :on-success="uploadSuccess"
            :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input type="number" v-model="temp.price" placeholder="价格" />
        </el-form-item>
       <!-- <el-form-item label="上架" prop="status">
          <el-switch
            v-model="temp.status"
            :active-value="0"
            :inactive-value="1"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </el-form-item>-->
        <el-form-item label="数量" prop="number">
          <el-input type="number" v-model="temp.number" placeholder="数量" />
        </el-form-item>
        <el-form-item label="标签" prop="label">
          <el-select v-model="temp.label" placeholder="请选择">
            <el-option
              v-for="item in labels"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="temp.description" placeholder="描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button v-if="dialogStatus!=='create'" type="danger" @click="handleDelete(item)">
          Delete
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          Confirm
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import * as product from '@/api/product'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { mapGetters } from "vuex";

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
      fileList: [],
      listQuery: {
        current: 1,
        size: 10,
        name: undefined,
        highPrice: undefined,
        minPrice: undefined,
        number: undefined,
        label: undefined,
        status: 0,
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
        img: [{ required: true, message: 'img is required', trigger: 'blur' }],
        price: [{ required: true, message: 'price is required', trigger: 'blur' }],
        number: [{ required: true, message: 'number is required', trigger: 'blur' }],
        /*status: [{ required: true, message: 'status is required', trigger: 'blur' }],*/
        label: [{ required: true, message: 'label is required', trigger: 'blur' }],
      },
      temp: {
        id: undefined,
        img: '',
        price: '',
        number: '',
        status: '0',
        label: '',
        description: '',
      },
      resetTemp() {
        this.temp = this.$options.data().temp
      },
      labels: [
        {value: '数码',label: '数码'},
        {value: '美妆',label: '美妆'},
        {value: '食品',label: '食品'},
        {value: '电器',label: '电器'},
      ],
      statuss: [
        {value: '0',label: '上架'},
        {value: '1',label: '下架'},
      ]
    }
  },
  created() {
    this.fetchData()
  },
  computed: {
    ...mapGetters(["fileUpload"])
  },
  methods: {
    fetchData() {
      this.listLoading = true
      var user = JSON.parse(localStorage.getItem('user'));
      this.listQuery.userId = user.id
      product.getList(this.listQuery).then(res => {
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
        this.temp.img = '';
        this.fileList = []
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          var user = JSON.parse(localStorage.getItem('user'));
          this.temp.userId = user.id
          product.create(this.temp).then(() => {
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

      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          product.update(tempData).then(() => {
            this.fetchData()
            this.dialogFormVisible = false
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
      product.remove(row.id).then(response => {
        this.fetchData()
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    handlePreview(file) {
      console.log(file);
    },
    uploadSuccess(response, file, fileList){
      this.temp.img = "http://localhost:8080/img/" + response.data
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG、PNG 格式!');
      }
      return isJPG;
    }
  }
}
</script>
