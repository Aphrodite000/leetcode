import java.util.*;
public class Solution {
    /*
    ### 回朔法的思想： 回朔法的重要思想在于： 通过枚举法，对所有可能性进行遍历。 但是枚举的顺序是 一条路走到黑，发现黑之后，退一步，再向前尝试没走过的路。直到所有路都试过。因此回朔法可以简单的理解为： 走不通就退一步的方枚举法就叫回朔法。而这里回退点也叫做回朔点。

### 回朔关键点 通过分析发现，回朔法实现的三大技术关键点分别是：

一条路走到黑
回退一步
另寻他路
### 关键点的实现 那么如何才能用代码实现上述三个关键点呢？

for 循环
递归
#### 解释如下

for循环的作用在于另寻他路： 你可以用for循环可以实现一个路径选择器的功能，该路径选择器可以逐个选择当前节点下的所有可能往下走下去的分支路径。 例如： 现在你走到了节点a，a就像个十字路口，你从上面来到达了a，可以继续向下走。若此时向下走的路有i条，那么你肯定要逐个的把这i条都试一遍才行。而for的作用就是可以让你逐个把所有向下的i个路径既不重复，也不缺失的都试一遍

递归可以实现一条路走到黑和回退一步： 一条路走到黑： 递归意味着继续向着for给出的路径向下走一步。 如果我们把递归放在for循环内部，那么for每一次的循环，都在给出一个路径之后，进入递归，也就继续向下走了。直到递归出口（走无可走）为止。 那么这就是一条路走到黑的实现方法。 递归从递归出口出来之后，就会实现回退一步。

因此for循环和递归配合可以实现回朔： 当递归从递归出口出来之后。上一层的for循环就会继续执行了。而for循环的继续执行就会给出当前节点下的下一条可行路径。而后递归调用，就顺着这条从未走过的路径又向下走一步。这就是回朔

说了这么多，回朔法的通常模板是什么呢？ 递归和for又是如何配合的呢？

#### 回朔代码模板


def backward():

    if (回朔点）：# 这条路走到底的条件。也是递归出口
        保存该结果
        return

    else:
        for route in all_route_set :  逐步选择当前节点下的所有可能route

            if 剪枝条件：
                剪枝前的操作
                return   #不继续往下走了，退回上层，换个路再走

            else：#当前路径可能是条可行路径

                保存当前数据  #向下走之前要记住已经走过这个节点了。例如push当前节点

                self.backward() #递归发生，继续向下走一步了。

                回朔清理     # 该节点下的所有路径都走完了，清理堆栈，准备下一个递归。例如弹出当前节点

这里剪枝操作指的是： 对于有些问题，你走着走着，若某种情况发生了，你就已经直到不能继续往下走了，再走也没有用了。而这个情况就被称之为剪枝条件。

而DFS就是一个最典型的回朔法的应用。
     */
    /*
   给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。
   */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> listAll=new ArrayList<List<Integer>>();
        List<Integer> list= new ArrayList<Integer>();
        //排序
        Arrays.sort(candidates);
        find(listAll,list,candidates,target,0);
        return listAll;
    }
    public void find(List<List<Integer>> listAll,List<Integer> tmp,int[] candidates,int target,int num){
        //递归的终点
        if(target==0){
            //递归的终点说明此list是有效的list
            listAll.add(tmp);
            return;
        }
        if(target<candidates[0]){
            //此list是无效的list，直接返回
            return ;
        }
        for(int i=num;i<candidates.length&&candidates[i]<=target;i++){
            //拷贝一份，不影响下次递归
            List<Integer> list=new ArrayList<>(tmp);
            list.add(candidates[i]);
            //递归运算，将i传到下一次运算是为了避免结果重复
            find(listAll,list,candidates,target-candidates[i],i);
        }
    }
}
