<template>
  <div class="app-container">

    <!--搜索表单-->
    <el-form :inline="true" :model="searchUser" class="demo-form-inline">
      <el-form-item label="昵称">
        <el-input
          v-model="searchUser.name"
          placeholder="请输入用户姓名"
        ></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="searchUser.sex" placeholder="请选择">
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="2"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="entrydate"
          clearable
          value-format="yyyy-MM-dd"
          type="daterange"
          placeholder="选择日期"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="width: 400px; margin-left: 20px"
        ></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
         <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>



    <!--按钮-->
    <el-row>
      <el-button type="danger" size="medium" @click="deleteByIds">- 批量删除</el-button>
      <el-button type="primary" size="medium" @click="dialogVisible = true;user = { avatar: ''};" >+ 新增</el-button>
    </el-row>

    <!--添加数据对话框表单-->
    <el-dialog ref="form" title="编辑用户" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="user" label-width="80px" size="mini">
        <el-form-item label="用户名">
          <el-input v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="user.phone"></el-input>
        </el-form-item>
        <el-form-item label="身份证">
          <el-input v-model="user.idNumber"></el-input>
        </el-form-item>
        <el-form-item label="性别" >
          <el-select v-model="user.sex" placeholder="请选择" style="width:100%" >
             <el-option
              v-for="item in sexList"
              :key="item.value"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="头像">
          <el-upload
            class="avatar-uploader"
            action="/api/upload"
            :headers="token"
            name="image"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="user.avatar" :src="user.avatar" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="user.status" placeholder="请选择" value-key="user.status" style="width:100%">
            <el-option
              v-for="item in statusList"
              :key="item.value"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="创建日期">
          <el-date-picker
            v-model="user.createTime"
            clearable
            type="date"
            placeholder="选择日期"
            size="small"
            style="width:100%"
          ></el-date-picker>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="add">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <br>
    <!--表格-->
    <template>
      <el-table :data="tableData" style="width: 100%" border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"  align="center"></el-table-column>
        <el-table-column  prop="name"  label="昵称"  align="center"></el-table-column>
        <el-table-column  prop="username"  label="用户名"  align="center"></el-table-column>
        <el-table-column prop="avatar" label="头像" align="center">
          <template slot-scope="{ row }">
            <el-image style="width: auto; height: 40px; border: none; cursor: pointer" :src="row.avatar"></el-image>
          </template>
        </el-table-column>

        <el-table-column align="center" label="性别">
          <template slot-scope="scope">
            <span style="margin-right: 10px">
            {{scope.row.sex == "1" ? "男" : "女"}}</span>
          </template>
        </el-table-column>
        <el-table-column  prop="phone"  label="手机号"  align="center"></el-table-column>
        <el-table-column align="center" label="状态">
          <template slot-scope="scope">
            <span style="margin-right: 10px" v-if="scope.row.status == 0">启用</span>
            <span style="margin-right: 10px" v-if="scope.row.status == 1">禁用</span>
          </template>
        </el-table-column>
        <el-table-column  prop="idNumber"  label="身份证号"  align="center"></el-table-column>
        <el-table-column align="center" label="创建日期">
          <template slot-scope="scope">
            {{scope.row.createTime ? scope.row.createTime.replace('T',' '):''}}
          </template>
        </el-table-column>
        <el-table-column align="center" label="更新日期">
          <template slot-scope="scope">
            {{scope.row.updateTime ? scope.row.updateTime.replace('T',' '):''}}
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="update(scope.row.id)">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <!--分页工具条-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :background="background"
      :current-page="currentPage"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="5"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount">
    </el-pagination>
  </div>
</template>

<script>
import { page, add, update, deleteById, selectById } from '@/api/user.js'
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
      searchUser: {
        name: '',
        sex: ''
      },
      user: {
        
      },
      sexList: [{ id: 1, name: '男' }, { id: 2, name: '女' }],
      statusList: [
        {
          id: 0,
          name: '启用'
        },
        {
          id: 1,
          name: '禁用'
        }
      ],

      createTime: '',
      updateTime: '',
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
  },
  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchUser.name,
        this.searchUser.sex,
        this.createTime,
        this.updateTime,
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
      this.searchUser = { name: '', sex: '' }
      this.createTime = ''
      this.updateTime = ''
      this.page()
    },
    // 添加数据
    add() {
      let operator

      if (this.user.id) {
        // 修改
        operator = update(this.user)
      } else {
        operator = add(this.user)
      }
      operator.then((resp) => {
        if (resp.data.code === 1) {
          this.dialogVisible = false
          this.page()
          this.$message({ message: '恭喜你，保存成功', type: 'success' })
          this.user = { avatar: '' }
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
          this.user = result.data.data
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

    // 删除用户信息
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

    // 批量删除用户信息
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

    // 文件上传相关
    handleAvatarSuccess(res, file) {
      this.user.avatar = res.data
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
        this.createTime = val[0]
        this.updateTime = val[1]
      } else {
        this.createTime = ''
        this.updateTime = ''
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
