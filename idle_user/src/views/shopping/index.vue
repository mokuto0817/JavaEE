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
          <div>¥{{item.price}}</div>
          <div>{{item.name}} 价格：¥{{item.price}}</div>
        </div>
      </el-col>
    </el-row>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="fetchData" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-image style="width: 100%; height: 100%"
                    :src="temp.img"
                    fit="contain"></el-image>
        </el-col>
        <el-col :span="12">
          <el-col :span="24" style="height: 30px;">商品名称：{{temp.name}}</el-col>
          <el-col :span="24" style="height: 30px;">商品价格：¥{{temp.price}}</el-col>
          <el-col :span="24" style="height: 30px;">商品标签：{{temp.label}}</el-col>
          <el-col :span="24" style="height: 30px;">商品描述：{{temp.description}}</el-col>
          <el-col :span="24" style="height: 60px;">购买数量：<el-input v-model="temp.number" type="number" placeholder="数量" @input="data1change" @change="data1change" style="width: 80px;height: 10px;" class="filter-item" /></el-col>
          <el-col :span="24" style="height: 30px;">购买总价：{{temp.number*temp.price}}</el-col>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button v-if="dialogStatus!=='create'" type="danger" @click="orderCreate(temp)">
          立即支付
        </el-button>
        <el-button type="primary" @click="removeData()">
          删除
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import * as shopping from '@/api/shopping'
import * as product from '@/api/product'
import * as order from '@/api/order'
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
        status: '0',
      },
      roles: ['ROLE_USER', 'ROLE_ADMIN'],
      dialogPluginVisible: false,
      pluginData: [],
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '商品详情',
        create: 'Create'
      },
      rules: {
        name: [{ required: true, message: 'name is required', trigger: 'blur' }],
        img: [{ required: true, message: 'img is required', trigger: 'blur' }],
        price: [{ required: true, message: 'price is required', trigger: 'blur' }],
        number: [{ required: true, message: 'number is required', trigger: 'blur' }],
        status: [{ required: true, message: 'status is required', trigger: 'blur' }],
        label: [{ required: true, message: 'label is required', trigger: 'blur' }],
      },
      temp: {
        id: undefined,
        img: undefined,
        price: undefined,
        number: undefined,
        status: '0',
        label: undefined,
        description: undefined,
        data1: 1,
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
    data1change(){
      if(this.temp.data1 > this.temp.number){
        this.temp.data1 = this.temp.number
      }
    },
    fetchData() {
      this.listLoading = true
      var user = JSON.parse(localStorage.getItem('user'));
      let data = {
        userId: user.id,
        type: 0
      }
      shopping.getList(data).then(res => {
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
      var user = JSON.parse(localStorage.getItem('user'));
      let data = {
        productId: this.temp.id,
        userId: user.id,
        number: 1,
        type: 0
      }
      shopping.create(data).then(() => {
        this.fetchData()
        this.dialogFormVisible = false
        this.$notify({
          title: 'Success',
          message: 'Created Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    removeData(){
      shopping.remove(this.temp.id).then(response => {
        this.fetchData()
        this.dialogFormVisible = false
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.data1 = 1
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    orderCreate(row) {
      var user = JSON.parse(localStorage.getItem('user'));
      let data = {
        productId: this.temp.productId,
        userId: user.id,
        data1: this.temp.data1,
        price: this.temp.data1 * this.temp.price
      }
      shopping.remove(this.temp.id).then(response => {
        order.createUser(data).then(response => {
          this.fetchData()
          this.dialogFormVisible = false
          this.$notify({
            title: 'Success',
            message: 'Delete Successfully',
            type: 'success',
            duration: 2000
          })
        })
      })

    },
  }
}
</script>
