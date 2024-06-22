<template>
  <div class="app-container"> 
    <p>暂未开发</p>
  </div>
</template>

<script>
import { page, add, update, deleteById, selectById } from '@/api/bookmark.js'
import { findAll } from '@/api/bmType.js'
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      background: true,
      // 每页显示的条数
      pageSize: 5,
      // 总记录数
      totalCount: 100,
      // 当前页码
      currentPage: 1,
      // 添加数据对话框是否展示的标记
      dialogVisible: false,
      // 品牌模型数据
      searchEmp: {
        name: '',
        gender: ''
      },
      emp: {
        image: ''
      },
      deptList: [],
      genderList: [{ id: 1, name: '男' }, { id: 2, name: '女' }],
      jobList: [
        {
          id: 1,
          name: '班主任'
        },
        {
          id: 2,
          name: '讲师'
        },
        {
          id: 3,
          name: '学工主管'
        },
        {
          id: 4,
          name: '教研主管'
        }
      ],

      beginTime: '',
      endTime: '',
      entrydate: '',

      // 被选中的id数组
      selectedIds: [],
      // 复选框选中数据集合
      multipleSelection: [],
      // 表格数据
      tableData: [],
      token: { token: getToken() }
    }
  },

  mounted() {
    this.page() // 当页面加载完成后，发送异步请求，获取数据
    findAll().then((result) => {
      this.deptList = result.data.data
    })
  },
  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchEmp.name,
        this.searchEmp.gender,
        this.beginTime,
        this.endTime,
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total
        this.tableData = res.data.data.rows
      })
    },

    // 复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    // 查询方法
    onSubmit() {
      this.currentPage = 1
      this.page()
    },

    clear() {
      this.searchEmp = { name: '', gender: '' }
      this.beginTime = ''
      this.endTime = ''
      this.entrydate = ''
      this.page()
    },
    // 添加数据
    add() {
      let operator

      if (this.emp.id) {
        // 修改
        operator = update(this.emp)
      } else {
        operator = add(this.emp)
      }

      operator.then((resp) => {
        if (resp.data.code === '1') {
          this.dialogVisible = false
          this.page()
          this.$message({ message: '恭喜你，保存成功', type: 'success' })
          this.emp = { image: '' }
        } else {
          this.$message.error(resp.data.msg)
        }
      })
    },
    update(id) {
      // 1. 打开窗口
      this.dialogVisible = true
      // 2. 发送请求

      selectById(id).then((result) => {
        if (result.data.code === 1) {
          this.emp = result.data.data
          this.emp
        }
      })
    },

    // 分页
    handleSizeChange(val) {
      // 重新设置每页显示的条数
      this.pageSize = val
      this.page()
    },

    handleCurrentChange(val) {
      // 重新设置当前页码
      this.currentPage = val
      this.page()
    },

    // 删除员工信息
    deleteById(id) {
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 2. 发送AJAX请求
        deleteById(id).then((resp) => {
          if (resp.data.code === 1) {
            // 删除成功
            this.$message.success('恭喜你，删除成功')
            this.page()
          } else {
            this.$message.error(resp.data.msg)
          }
        })
      }).catch(() => {
        // 用户点击取消按钮
        this.$message.info('已取消删除')
      })
    },

    // 批量删除员工信息
    deleteByIds() {
      // 弹出确认提示框
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 用户点击确认按钮
        // 1. 创建id数组, 从 this.multipleSelection 获取即可
        for (let i = 0; i < this.multipleSelection.length; i++) {
          this.selectedIds[i] = this.multipleSelection[i].id
        }

        // 2. 发送AJAX请求
        deleteById(this.selectedIds).then((resp) => {
          if (resp.data.code === '1') {
            // 删除成功
            this.$message.success('恭喜你，删除成功')
            this.page()
          } else {
            this.$message.error(resp.data.msg)
          }
        })
      }).catch(() => {
        // 用户点击取消按钮
        this.$message.info('已取消删除')
      })
    },

    // 文件上传相关
    handleAvatarSuccess(res, file) {
      this.emp.image = res.data
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  },

  watch: {
    entrydate(val) {
      if (val && val.length >= 2) {
        this.beginTime = val[0]
        this.endTime = val[1]
      } else {
        this.beginTime = ''
        this.endTime = ''
      }
    }
  }
}
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>
