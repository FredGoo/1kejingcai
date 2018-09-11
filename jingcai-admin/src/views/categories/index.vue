<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button type="success" class="filter-item" @click="showCategory">添加</el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="#" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="名称">
        <template slot-scope="scope">
          {{ scope.row.cTitle }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="showCategory(scope.$index)">修改</el-button>
          <el-button type="danger" @click="deleteCategory(scope.row.nId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="类别详情" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="form.cTitle" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="关联产品" :label-width="formLabelWidth">
          <el-select v-model="form.productList" multiple placeholder="请选择">
            <el-option
              v-for="item in productList"
              :key="item.nId"
              :label="item.cTitle"
              :value="item.nId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateCategory">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {
    getList,
    update,
    deleteItem,
    getProductByCategory,
    getAllProduct,
    saveProductAndCategoryRelation
  } from '@/api/category'

  export default {
    data() {
      return {
        list: null,
        listLoading: true,

        productList: [],

        dialogFormVisible: false,
        formLabelWidth: '100px',
        form: {
          nId: null,
          cTitle: '',
          productList: []
        }
      }
    },
    created() {
      this.fetchData()
      this.fetchProducts()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getList().then(response => {
          this.list = response.result
          this.listLoading = false
        })
      },
      fetchProducts() {
        getAllProduct().then(response => {
          this.productList = response.result
        })
      },
      showCategory(index) {
        if (this.list[index]) {
          this.form.nId = this.list[index].nId
          this.form.cTitle = this.list[index].cTitle
          this.form.productList = []

          getProductByCategory(this.form.nId).then(response => {
            for (let item of response.result) {
              this.form.productList.push(item.nId)
            }
          })
        } else {
          this.form = {
            nId: null,
            cTitle: '',
            productList: []
          }
        }

        this.dialogFormVisible = true
      },
      // 保存类别
      updateCategory() {
        let data = this.form

        update(data).then(response => {
          let productCategoryRelation = {
            categoryId: data.nId,
            productIdList: data.productList
          }
          saveProductAndCategoryRelation(productCategoryRelation).then(response => {
            this.fetchData()
            this.dialogFormVisible = false
          })
        })
      },
      deleteCategory(id) {
        this.$confirm('此操作将永久删除该类别, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteItem(id).then(response => {
            this.fetchData()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })

      }
    }
  }
</script>

<style scoped>
  .filter-container {
    padding-bottom: 10px;
  }

  .filter-container .filter-item {
    display: inline-block;
    margin-bottom: 10px;
    vertical-align: middle;
  }
</style>
