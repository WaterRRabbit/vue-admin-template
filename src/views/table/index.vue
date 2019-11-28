<template>
  <div class="app-container">
    <div class="header-container">
      <el-button class="header-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="Id">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="Username" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Create Time" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
        </template>
      </el-table-column>
      <el-table-column label="Modified Time" align="center">
        <template slot-scope="scope">
          {{ scope.row.modifiedTime | parseTime('{y}-{m}-{d} {h}:{i}') }}
        </template>
      </el-table-column>
      <el-table-column label="state" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.state | statusFilter">
            {{ scope.row.state | statusFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="handleEdit(scope.$index, scope.row)" />
          <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.$index, scope.row)" />
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        :page-size="pageSize"
        :current-page="currentPage"
        :page-sizes="[10, 20, 50, 100]"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <!--  -->
    <dialog-form
      ref="dialogForm"
    />
  </div>
</template>

<script>

import { getList, del } from '@/api/user'
import { parseTime } from '@/utils'

import DialogForm from './components/DialogForm'

import Swal from 'sweetalert2'

export default {
  components: { DialogForm },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        0: 'gray',
        2: 'danger'
      }
      return statusMap[status]
    },
    parseTime(cFormat) {
      return parseTime(cFormat)
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      total: 0,
      currentPage: 1,
      pageSize: 10
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList(this.currentPage, this.pageSize).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        this.listLoading = false
      })
    },
    handleEdit(index, row) {
      this.$refs.dialogForm.onOpen(row)
      console.log(index, row)
    },
    handleCreate() {
      this.$refs.dialogForm.onOpen()
    },
    handleDelete(index, row) {
      Swal.fire({
        title: 'Are you sure?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.value) {
          del(row.id).then(response => {
            console.log(response)
            Swal.fire({
              title: 'Deleted!',
              icon: 'success'
            })
          })
        }
      })
    },
    handleSizeChange: function(pageSize) {
      this.pageSize = pageSize
      this.handleCurrentChange(this.currentPage)
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage
      this.currentChangePage()
    },
    // 分页方法（重点）
    currentChangePage() {
      this.listLoading = true
      getList(this.currentPage, this.pageSize).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        this.listLoading = false
      })
    }
  }
}
</script>

<style lang="scss">
  .pagination{
    &-container{
      margin-top: 20px;
    }
  }
  .header{
    &-container{
      padding: 10px 0;
    }
    &-item{
      margin-left: 0!important;
    }
  }
</style>
