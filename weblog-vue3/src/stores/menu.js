import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useMenuStore = defineStore('menu', () => {
  // 左边栏菜单默认宽度
  const menuWidth = ref("250px")
  
  // 展开或伸缩左边栏菜单
  function handleMenuWidth() {
      menuWidth.value = menuWidth.value == '250px' ? '64px' : '250px'
  }
  
  return { menuWidth, handleMenuWidth }
}, 
{
  // 开启持久化
  persist: true,
}
)

// 上述代码中，我们先是引入了 pinia 的defineStore() 函数，它用户声明 store。它的第一个参数是个字符串，通过它定义了一个名为 menu 的全局 store, 注意，名称在应用中必须是唯一的 。另外，将返回的函数命名为 use... 这个格式，它符合组合式函数风格的约定。

// defineStore() 的第二个参数可接受两类值：Setup 函数或 Option 对象。上面的代码中，我们在函数内声明了一个 menuWidth 菜单宽度的全局变量，并且是响应式的；然后还定义了一个展开/收缩菜单栏的方法，在该方法中，若当前菜单宽度为 250px 则赋值为 64px, 表示收缩；反之，则展开。最后，将 menuWidth 变量和 handleMenuWidth 方法返回，以供外界调用。
