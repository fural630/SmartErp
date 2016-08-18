<!-- paging　start -->
<input type="hidden" id="moduleUrl" value="${requestUrl}"/>
<div class="current_nav_name clearfix">用户信息管理
	<div class="fr small_size"> <a class="btn"><img src="/design/frame/style/img/add.png"/>新增</a>
	</div>
</div>  
<div class="paging clearfix">
	<div class="paging_inner">
		<div class="fl page_box">
			<a class="pging_btn" onclick="previousPage(${page.totalPage})">上一页</a> &nbsp;&nbsp;
			第&nbsp;<input class="txt width_20px ta_c" value="${page.pageNo}" id="pageNo"/>&nbsp;页 &nbsp; 
			<a class="pging_btn" onclick="nextPage(${page.totalPage})">下一页 </a>
			&nbsp;共&nbsp;${page.totalRecord}&nbsp;条， ${page.pageNo}/${page.totalPage} 页， 每页 
			<select class="sel" id="pageSize" onchange="changePageSize()">
				<#list page.pageList as pageSize>
					<option value="${pageSize}" <#if pageSize == page.pageSize> selected="selected" </#if>>${pageSize}</option>
				</#list>
			</select>&nbsp;条
		</div>
		<div class="fr">
			<a class="btn">重置</a> <a class="btn" onclick="queryMainPage()"><img src="/design/frame/style/img/query.png" />查询</a>
		</div>
	</div>
</div>
<!-- paging　end -->
