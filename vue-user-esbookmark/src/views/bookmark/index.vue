<template>
  <ul class="infinite-list" v-infinite-scroll="page" infinite-scroll-delay="125" style="overflow:auto;height: 100vh;">
    <!-- <li >{{ bookmark }}</li> -->
    <div class="card infinite-list-item" v-for="bookmark in bookmarkList">
      <div class="top">
        <img class="image" :src="bookmark.image">
        <p class="title">{{bookmark.title }}</p>
        <p class="type" v-for="item in typeList" :key="item.id"  v-if="bookmark.typeId === item.id">{{ item.type }}</p>
      </div>
      <div class="body">
        <span class="username">{{ bookmark.url }}</span>
        <p class="text">{{ bookmark.description }}</p>
        <span class="username">来自用户: {{bookmark.username}} </span>
          <div class="footer">
            <div>
              <div><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"><g stroke-width="0" id="SVGRepo_bgCarrier"></g><g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g><g id="SVGRepo_iconCarrier"> <path stroke-linejoin="round" stroke-linecap="round" stroke-width="1.5" d="M16 10H16.01M12 10H12.01M8 10H8.01M3 10C3 4.64706 5.11765 3 12 3C18.8824 3 21 4.64706 21 10C21 15.3529 18.8824 17 12 17C11.6592 17 11.3301 16.996 11.0124 16.9876L7 21V16.4939C4.0328 15.6692 3 13.7383 3 10Z"></path> </g></svg>18</div>
              <div><svg fill="#000000" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="-2.5 0 32 32"><g stroke-width="0" id="SVGRepo_bgCarrier"></g><g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g><g id="SVGRepo_iconCarrier"> <g id="icomoon-ignore"> </g> <path fill="#000000" d="M0 10.284l0.505 0.36c0.089 0.064 0.92 0.621 2.604 0.621 0.27 0 0.55-0.015 0.836-0.044 3.752 4.346 6.411 7.472 7.060 8.299-1.227 2.735-1.42 5.808-0.537 8.686l0.256 0.834 7.63-7.631 8.309 8.309 0.742-0.742-8.309-8.309 7.631-7.631-0.834-0.255c-2.829-0.868-5.986-0.672-8.686 0.537-0.825-0.648-3.942-3.3-8.28-7.044 0.11-0.669 0.23-2.183-0.575-3.441l-0.352-0.549-8.001 8.001zM1.729 10.039l6.032-6.033c0.385 1.122 0.090 2.319 0.086 2.334l-0.080 0.314 0.245 0.214c7.409 6.398 8.631 7.39 8.992 7.546l-0.002 0.006 0.195 0.058 0.185-0.087c2.257-1.079 4.903-1.378 7.343-0.836l-13.482 13.481c-0.55-2.47-0.262-5.045 0.837-7.342l0.104-0.218-0.098-0.221-0.031 0.013c-0.322-0.632-1.831-2.38-7.498-8.944l-0.185-0.215-0.282 0.038c-0.338 0.045-0.668 0.069-0.981 0.069-0.595 0-1.053-0.083-1.38-0.176z"> </path> </g></svg>7</div>
              <div><svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path opacity="0.1" d="M21 6C21 7.65685 19.6569 9 18 9C16.3431 9 15 7.65685 15 6C15 4.34315 16.3431 3 18 3C19.6569 3 21 4.34315 21 6Z" fill="#323232"></path> <path opacity="0.1" d="M21 18C21 19.6569 19.6569 21 18 21C16.3431 21 15 19.6569 15 18C15 16.3431 16.3431 15 18 15C19.6569 15 21 16.3431 21 18Z" fill="#323232"></path> <path opacity="0.1" d="M9 12C9 13.6569 7.65685 15 6 15C4.34315 15 3 13.6569 3 12C3 10.3431 4.34315 9 6 9C7.65685 9 9 10.3431 9 12Z" fill="#323232"></path> <path d="M21 6C21 7.65685 19.6569 9 18 9C16.3431 9 15 7.65685 15 6C15 4.34315 16.3431 3 18 3C19.6569 3 21 4.34315 21 6Z" stroke-width="2"></path> <path d="M21 18C21 19.6569 19.6569 21 18 21C16.3431 21 15 19.6569 15 18C15 16.3431 16.3431 15 18 15C19.6569 15 21 16.3431 21 18Z" stroke-width="2"></path> <path d="M9 12C9 13.6569 7.65685 15 6 15C4.34315 15 3 13.6569 3 12C3 10.3431 4.34315 9 6 9C7.65685 9 9 10.3431 9 12Z" stroke-width="2"></path> <path d="M8.7207 10.6397L15.0001 7.5" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path> <path d="M8.70605 13.353L15 16.5" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path> </g></svg>2</div>
            </div>
            <!-- 点赞 -->
            <label class="container">
              <input type="checkbox">
              <svg  v-if="bookmark.isLike!=1" @click.stop="like(bookmark)" version="1.1" viewBox="0 0 32 32" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><path d="M29.845,17.099l-2.489,8.725C26.989,27.105,25.804,28,24.473,28H11c-0.553,0-1-0.448-1-1V13  c0-0.215,0.069-0.425,0.198-0.597l5.392-7.24C16.188,4.414,17.05,4,17.974,4C19.643,4,21,5.357,21,7.026V12h5.002  c1.265,0,2.427,0.579,3.188,1.589C29.954,14.601,30.192,15.88,29.845,17.099z" id="XMLID_254_"></path><path d="M7,12H3c-0.553,0-1,0.448-1,1v14c0,0.552,0.447,1,1,1h4c0.553,0,1-0.448,1-1V13C8,12.448,7.553,12,7,12z   M5,25.5c-0.828,0-1.5-0.672-1.5-1.5c0-0.828,0.672-1.5,1.5-1.5c0.828,0,1.5,0.672,1.5,1.5C6.5,24.828,5.828,25.5,5,25.5z" id="XMLID_256_"></path></svg>
              <svg  v-else class="isOrDisLike" @click.stop="like(bookmark)" id="Glyph" version="1.1" viewBox="0 0 32 32" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><path d="M29.845,17.099l-2.489,8.725C26.989,27.105,25.804,28,24.473,28H11c-0.553,0-1-0.448-1-1V13  c0-0.215,0.069-0.425,0.198-0.597l5.392-7.24C16.188,4.414,17.05,4,17.974,4C19.643,4,21,5.357,21,7.026V12h5.002  c1.265,0,2.427,0.579,3.188,1.589C29.954,14.601,30.192,15.88,29.845,17.099z" id="XMLID_254_"></path><path d="M7,12H3c-0.553,0-1,0.448-1,1v14c0,0.552,0.447,1,1,1h4c0.553,0,1-0.448,1-1V13C8,12.448,7.553,12,7,12z   M5,25.5c-0.828,0-1.5-0.672-1.5-1.5c0-0.828,0.672-1.5,1.5-1.5c0.828,0,1.5,0.672,1.5,1.5C6.5,24.828,5.828,25.5,5,25.5z" id="XMLID_256_"></path></svg>
            </label>

            <!-- 不喜欢 -->
            <label class="container">
              <input type="checkbox">
              <svg v-if="bookmark.isLike!=-1" @click.stop="disLike(bookmark)" id="Glyph" version="1.1" viewBox="0 0 32 32" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><path d="M2.156,14.901l2.489-8.725C5.012,4.895,6.197,4,7.528,4h13.473C21.554,4,22,4.448,22,5v14  c0,0.215-0.068,0.425-0.197,0.597l-5.392,7.24C15.813,27.586,14.951,28,14.027,28c-1.669,0-3.026-1.357-3.026-3.026V20H5.999  c-1.265,0-2.427-0.579-3.188-1.589C2.047,17.399,1.809,16.12,2.156,14.901z" id="XMLID_259_"></path><path d="M25.001,20h4C29.554,20,30,19.552,30,19V5c0-0.552-0.446-1-0.999-1h-4c-0.553,0-1,0.448-1,1v14  C24.001,19.552,24.448,20,25.001,20z M27.001,6.5c0.828,0,1.5,0.672,1.5,1.5c0,0.828-0.672,1.5-1.5,1.5c-0.828,0-1.5-0.672-1.5-1.5  C25.501,7.172,26.173,6.5,27.001,6.5z" id="XMLID_260_"></path></svg>
              <svg v-else class="isOrDisLike" @click.stop="disLike(bookmark)" id="Glyph" version="1.1" viewBox="0 0 32 32" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><path d="M2.156,14.901l2.489-8.725C5.012,4.895,6.197,4,7.528,4h13.473C21.554,4,22,4.448,22,5v14  c0,0.215-0.068,0.425-0.197,0.597l-5.392,7.24C15.813,27.586,14.951,28,14.027,28c-1.669,0-3.026-1.357-3.026-3.026V20H5.999  c-1.265,0-2.427-0.579-3.188-1.589C2.047,17.399,1.809,16.12,2.156,14.901z" id="XMLID_259_"></path><path d="M25.001,20h4C29.554,20,30,19.552,30,19V5c0-0.552-0.446-1-0.999-1h-4c-0.553,0-1,0.448-1,1v14  C24.001,19.552,24.448,20,25.001,20z M27.001,6.5c0.828,0,1.5,0.672,1.5,1.5c0,0.828-0.672,1.5-1.5,1.5c-0.828,0-1.5-0.672-1.5-1.5  C25.501,7.172,26.173,6.5,27.001,6.5z" id="XMLID_260_"></path></svg>
            </label>
            <button class="addButton" @click="addById(bookmark)">Add now</button>
          </div>
        </div>
    </div>
  </ul>
</template>

<script>
import { page,add} from '@/api/bookmark.js'
import {findAll} from '@/api/bmType.js'
import {like,disLike,deleteLike,deleteDisLike} from '@/api/redis.js'
export default {
  
  name: 'bookmark',
  data () {
      return {
        bookmarkList: [],
        // 每页显示的条数
        pageSize: 1,
        // 总记录数
        totalCount: 100,
        // 当前页码
        currentPage: 1,
        typeList: []
      }
    },
    methods: {
      // 查询分页数据
      page() {
        page(
          this.currentPage,
          this.pageSize,
          this.status=1
        ).then((res) => {
          this.totalCount = res.data.data.total
          var list=res.data.data.rows
          list.forEach(element => {
            this.bookmarkList.push(element)
          });
          this.currentPage+=1
          
        })
      },
      // 添加数据到自身
      addById(bookmark){
        bookmark.status=0;
        let operator= add(bookmark)
        operator.then((resp) => {
          if (resp.data.code === 1) {
            this.dialogVisible = false
            this.$message({ message: '恭喜你，保存成功', type: 'success' })
            this.currentPage=1;
            this.page()
          } else {
            this.$message.error(resp.data.msg)
          }
        })
      },
      //点赞
      like(bookmark){
        //bookmark.isLike=bookmark.isLike==1?0:1
        var value=bookmark.isLike
        if(value==1){
          deleteLike(bookmark.id)
          bookmark.isLike=0
        }else if(value==0){
          like(bookmark.id)
          bookmark.isLike=1
        }else{
          deleteDisLike(bookmark.id)
          like(bookmark.id)
          bookmark.isLike=1
        }
      },
      //不喜欢
      disLike(bookmark){
        //bookmark.isLike=bookmark.isLike==-1?0:-1
        var value=bookmark.isLike
        if(value==-1){
          deleteDisLike(bookmark.id)        
          bookmark.isLike=0
        }else if(value==0){
          disLike(bookmark.id)
          bookmark.isLike=-1
        }else{
          deleteLike(bookmark.id)
          disLike(bookmark.id)
          bookmark.isLike=-1
        }
    },
  },
  mounted () {
    findAll().then((result) => {
      this.typeList = result.data.data;
    });
  }
}
</script>

<style lang="scss" scoped>

.infinite-list::-webkit-scrollbar {
    width: 10px;
    height: 10px;
}
.infinite-list::-webkit-scrollbar-thumb {
    background: whitesmoke;
    border-radius: 5px;
}
.infinite-list::-webkit-scrollbar-track {
    background-color: rgba(26, 25, 24, 0.6);
    border: 1px solid #ccc;
}
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.top{
  display: flex;
  font-size: 32px;
  margin-bottom: 5px;
  color: whitesmoke;
}
.top .image{
  width: 50px;
  height: 50px;
  border-radius: 50%;
  overflow: hidden;
}
.top .title{
  margin-left: 12px;
}
.top .type{
  position: absolute;
  right:20px;
}
.top p{
  font-size: 32px;
  line-height: 50px;
  margin: 0;
  padding: 0;
}

.card {
  position: relative;
  margin-top: 25px;
  background-color: #30344c;
  padding: 1em;
  z-index: 5;
  font-size: 28px;
  box-shadow: 4px 4px 20px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  width: 90%;
  float: left;
  margin-left: 5%;
  flex-wrap: wrap;  //允许换行
  transition: 200ms ease-in-out;
}

.username {
  color: #C6E1ED;
  font-size: 0.85em;
  font-weight: 600;
}

.body {
  display: flex;
  flex-direction: column;
}

.body .text {
  margin: 0 10px 0 0;
  white-space: pre-line;
  color: #c0c3d7;
  font-weight: 400;
  line-height: 1.5;
  margin-bottom: 4px;
}


.footer {
  position: relative;
  width: 100%;
  color: #9fa4aa;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border: none;
  margin-top: 10px;
}

.footer div {
  margin-right: 1rem;
  height: 20px;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.footer svg {
  margin-right: 5px;
  height: 100%;
  stroke: #9fa4aa;
}




/* this button is inspired from this -- s://www.newline.co/pricing */
.addButton {
  --width: 150px;
  --timing: 2s;
  border: 0;
  width: var(--width);
  margin-left: 18px;
  padding-block: 1em;
  color: #fff;
  font-weight: bold;
  font-size: 1em;
  background: rgb(127, 191, 199);
  transition: all 0.2s;
  border-radius: 3px;
  cursor: pointer;
}

.addButton:hover {
  background-image: linear-gradient(
    to right,
    rgb(250, 82, 82),
    rgb(250, 82, 82) 16.65%,
    rgb(190, 75, 219) 16.65%,
    rgb(190, 75, 219) 33.3%,
    rgb(76, 110, 245) 33.3%,
    rgb(76, 110, 245) 49.95%,
    rgb(64, 192, 87) 49.95%,
    rgb(64, 192, 87) 66.6%,
    rgb(250, 176, 5) 66.6%,
    rgb(250, 176, 5) 83.25%,
    rgb(253, 126, 20) 83.25%,
    rgb(253, 126, 20) 100%,
    rgb(250, 82, 82) 100%
  );
  animation: var(--timing) linear dance6123 infinite;
  transform: scale(1.1) translateY(-1px);
}
.addButton:active{
  transform: scale(0.9);
}
@keyframes dance6123 {
  to {
    background-position: var(--width);
  }
}




//点赞和不喜欢
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

.container {
  display: block;
  position: relative;
  cursor: pointer;
  user-select: none;
}

svg {
  position: relative;
  top: 0;
  left: 0;
  height: 50px;
  width: 50px;
  transition: all 0.3s;
  fill: #666;
}

svg:hover {
  transform: scale(1.1) rotate(-10deg);
}

.container .isOrDisLike{
  fill: #2196F3;
}



</style>
