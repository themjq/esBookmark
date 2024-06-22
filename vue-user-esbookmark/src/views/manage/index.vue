<template>
  <div class="app-container">

    <!--搜索表单-->
    <el-form :inline="true" :model="searchBookmark" class="demo-form-inline">
      <el-form-item label="标题">
        <el-input
          v-model="searchBookmark.title"
          placeholder="请输入标题"
        ></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="searchBookmark.status" placeholder="请选择">
          <el-option label="私密" value="0"></el-option>
          <el-option label="共享" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="searchBookmark.typeId" placeholder="请选择">
          <el-option v-for="item in typeList" :key="item.id" :label="item.type" :value="item.id">{{ item.type }}</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="更新时间">
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
      <el-button type="primary" size="medium" @click="dialogVisible = true;bookmark = { image: ''};" >+ 新增</el-button>
    </el-row>

    <!--添加数据对话框表单-->
    <el-dialog ref="form" title="编辑书签" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="bookmark" label-width="80px" size="mini">
        <el-form-item label="标题">
          <el-input v-model="bookmark.title"></el-input>
        </el-form-item>
        <el-form-item label="Url">
          <el-input v-model="bookmark.url"></el-input>
        </el-form-item>
        <el-form-item label="类型" >
          <el-select v-model="bookmark.typeId" placeholder="请选择" style="width:100%" >
             <el-option
              v-for="item in typeList"
              :key="item.value"
              :label="item.type"
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
            <img v-if="bookmark.image" :src="bookmark.image" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="bookmark.status" placeholder="请选择" value-key="bookmark.status" style="width:100%">
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
            v-model="bookmark.createTime"
            clearable
            type="date"
            placeholder="选择日期"
            size="small"
            style="width:100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="bookmark.description"></el-input>
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
        <el-table-column  prop="id"  label="书签ID"  align="center"></el-table-column>
        <el-table-column  prop="title"  label="标题"  align="center"></el-table-column>
        <el-table-column prop="image" label="网站图片" align="center">
          <template slot-scope="{ row }">
            <el-image style="width: auto; height: 40px; border: none; cursor: pointer" :src="row.image"></el-image>
          </template>
        </el-table-column>

        <el-table-column align="center" label="状态">
          <template slot-scope="scope">
            <span style="margin-right: 10px">
            {{scope.row.status == "0" ? "私密" : "共享"}}</span>
          </template>
        </el-table-column>
        <el-table-column  prop="url"  label="Url"  align="center"></el-table-column>
        <el-table-column align="center" label="类型">
          <template slot-scope="scope">
            <span v-for="item in typeList" :key="item.id" style="margin-right: 10px" v-if="scope.row.typeId === item.id">
              {{ item.type }}
            </span>
          </template>
        </el-table-column>
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
import { page2, add, update, deleteById, selectById } from '@/api/bookmark.js'
import {findAll} from '@/api/bmType.js'
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
      searchBookmark: {
        title: '',
        status: '',
        typeId:''
      },
      bookmark: {
        
      },
      typeList: [],
      statusList: [
        {
          id: 0,
          name: '私密'
        },
        {
          id: 1,
          name: '共享'
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
    findAll().then((result) => {
      this.typeList = result.data.data;
      //console.log(this.typeList)
    });
    
  },
  methods: {
    // 查询分页数据
    page() {
      page2(
        this.searchBookmark.title,
        this.searchBookmark.status,
        this.searchBookmark.typeId,
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
      console.log(this.searchBookmark)
      this.currentPage = 1
      this.page()
    },

    clear() {
      this.searchBookmark = { title: '', status: '',typeId:'' }
      this.createTime = ''
      this.updateTime = ''
      this.page()
    },
    // 添加数据
    add() {
      let operator

      if (this.bookmark.id) {
        // 修改
        operator = update(this.bookmark)
      } else {
        operator = add(this.bookmark)
      }
      operator.then((resp) => {
        if (resp.data.code === 1) {
          this.dialogVisible = false
          this.page()
          this.$message({ message: '恭喜你，保存成功', type: 'success' })
          this.bookmark = { image: '' }
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
          this.bookmark = result.data.data
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

    // 删除书签信息
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
        // 书签点击取消按钮
        this.$message.info('已取消删除')
      })
    },

    // 批量删除书签信息
    deleteByIds() {
      // 弹出确认提示框
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 书签点击确认按钮
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
        // 书签点击取消按钮
        this.$message.info('已取消删除')
      })
    },

    // 文件上传相关
    handleAvatarSuccess(res, file) {
      this.bookmark.image = res.data
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
