<!-- paging　start -->
<div class="paging clearfix">
	<div class="paging_inner">
		<div class="fl page_box">
			<a class="pging_btn" onclick="previousPage()">上一页</a> &nbsp;&nbsp;
			第&nbsp;<input class="txt width_20px ta_c" value="${page.pageNo}" id="pageNo" name="pageNo"/>&nbsp;页 &nbsp; 
			<a class="pging_btn" onclick="nextPage()">下一页 </a>
			&nbsp;共&nbsp;<span id="totalRecord">${page.totalRecord}</span>&nbsp;条，<span id="txtPageNo">${page.pageNo}</span>/<span id="totalPage">${page.totalPage}</span>页， 每页 
			<select class="sel" id="pageSize" name="pageSize" onchange="changePageSize()">
				<#list page.pageList as pageSize>
					<option value="${pageSize}" <#if pageSize == page.pageSize> selected="selected" </#if>>${pageSize}</option>
				</#list>
			</select>&nbsp;条
		</div>
		<div class="fr">
			<a class="btn" onclick="resetAll()">重置</a> <a class="btn" onclick="queryMainPage()"><img src="/design/frame/style/img/query.png" />查询</a>
		</div>
	</div>
</div>
<!-- paging　end -->
