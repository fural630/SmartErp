<!-- paging　start -->
<input type="hidden" id="moduleUrl" value="${requestUrl}"/>
<div class="paging clearfix">
	<div class="paging_inner">
		<div class="fl page_box">
			<a class="pging_btn pging_disable">上一页</a> &nbsp;&nbsp;
			第&nbsp;<input class="txt width_20px ta_c" value="${page.pageNo}" id="modulePagePageNo"/>&nbsp;页 &nbsp; 
			<a class="pging_btn">下一页 </a>
			&nbsp;共&nbsp;${page.totalRecord}&nbsp;条， ${page.pageNo}/${page.totalPage} 页， 每页 
			<select class="sel" id="modulePagePageSize">
				<option>10</option>
				<option>20</option>
				<option>30</option>
			</select>&nbsp;条
		</div>
		<div class="fr">
			<a class="btn">重置</a> <a class="btn" onclick="queryMainPage()"><img src="design/frame/style/img/query.png" />查询</a>
		</div>
	</div>
</div>
<!-- paging　end -->
