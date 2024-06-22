<template>
  <div class="mainRank"> 
    <table>
        <!-- 表头 -->
        <tr>
            <th>书签ID</th>
            <th>标题</th>
            <th>书签图片</th>
            <th>上传用户</th>
            <th>类型</th>
            <th>链接</th>
        </tr>
        <!-- 数据 -->
        <tr v-for="rank in rankData">
            <td>{{ rank.id }}</td>
            <td>{{ rank.title }}</td> 
            <td><img class="image" :src="rank.image"></td>
            <td>{{ rank.username }}</td>
            <td>{{ rank.type }}</td>
            <td>{{ rank.url }}</td>
        </tr>
    </table>
  </div>
</template>

<script>
import {like,disLike,deleteLike,deleteDisLike,getRedisRank} from '@/api/redis.js'
export default {
  data() {
    return {
      rankData:'',
    }
  },

  mounted() {
    
    let operator= getRedisRank()
      operator.then((resp) => {
        if (resp.data.code === 1) {
          //console.log(resp.data.data)
          this.rankData=resp.data.data
          console.log(this.rankData)
        } else {
          this.$message.error(resp.data.msg)
        }
      })

  },
  methods: {

  }
}
</script>
<style>
.image{
  width: 50px;
  height: 50px;
  overflow: hidden;
}
.mainRank{
    padding-top: 25px;
    flex-wrap: wrap; /* 允许子元素换行 */
    padding-bottom: 75px;
    max-height: 1000px;
    overflow-y: auto; /* 当内容超出容器时，显示垂直滚动条 */ 
}
.mainRank::-webkit-scrollbar {
    width: 10px;
    height: 10px;
}
.mainRank::-webkit-scrollbar-thumb {
    background: whitesmoke;
    border-radius: 5px;
}
.mainRank::-webkit-scrollbar-track {
    background-color: rgba(26, 25, 24, 0.6);
    border: 1px solid #ccc;
}
.mainRank table{
    background-color: rgba(26, 25, 24, 0.8);
    width: 90%;
    margin-left: 5%;
    text-align: center;
    border-radius: 5px;
    color: antiquewhite;
}
.mainRank th{
    background-color: rgba(26, 25, 24, 0.9);
}
.mainRank th,tr,td{
    padding: 15px;
    border-radius: 5px;
    transition: all 0.5s;
}
.mainRank tr:nth-child(2n) td{
    background-color: #282828;
}
.mainRank td:hover{
    color: darkgrey;
}
.mainRank td:active{
    font-size: 12px;
}

</style>
