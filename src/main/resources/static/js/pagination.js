(function () {
    const PAGE_SIZE = 12;    // 한 페이지에 보여줄 데이터 개수
    const GROUP_SIZE = 10;   // 페이지 버튼 그룹 크기

    function calculatePageGroup(currentPage, totalPages) {
        const startPage = Math.floor((currentPage - 1) / GROUP_SIZE) * GROUP_SIZE + 1;
        const endPage = Math.min(startPage + GROUP_SIZE - 1, totalPages);
        return {startPage, endPage};
    }

    function createPaginationElements($container, startPage, endPage, currentPage, hasPreviousGroup, hasNextGroup) {
        const prevDisabled = hasPreviousGroup ? '' : 'disabled';
        const nextDisabled = hasNextGroup ? '' : 'disabled';

        $container.append(`
            <button class="btn btn-outline-primary" id="prevBtn" ${prevDisabled}>이전</button>
            <div id="pageButtons"></div>
            <button class="btn btn-outline-primary" id="nextBtn" ${nextDisabled}>다음</button>
        `);

        const $pageButtons = $('#pageButtons');
        for (let i = startPage; i <= endPage; i++) {
            const activeClass = (i === currentPage) ? 'active' : '';
            $pageButtons.append(`
                <button class="btn btn-outline-secondary pageBtn ${activeClass}" data-page="${i}">
                    ${i}
                </button>
            `);
        }
    }

    function bindEvents(startPage, hasPreviousGroup, hasNextGroup) {
        $('#prevBtn').off('click').on('click', () => {
            if (hasPreviousGroup) {
                const prevPageGroupStart = startPage - GROUP_SIZE;
                listing(prevPageGroupStart, PAGE_SIZE);
            }
        });

        $('#nextBtn').off('click').on('click', () => {
            if (hasNextGroup) {
                const nextPageGroupStart = startPage + GROUP_SIZE;
                listing(nextPageGroupStart, PAGE_SIZE);
            }
        });

        $('.pageBtn').off('click').on('click', function () {
            const selectedPage = parseInt($(this).data('page'));
            listing(selectedPage, PAGE_SIZE);
        });
    }

    function renderPagination(response) {
        const {number, totalPages} = response;
        const currentPage = number + 1;
        const $paginationContainer = $('#pagination-container');

        $paginationContainer.empty();

        const {startPage, endPage} = calculatePageGroup(currentPage, totalPages);

        const hasPreviousGroup = startPage > 1;
        const hasNextGroup = endPage < totalPages;

        createPaginationElements($paginationContainer, startPage, endPage, currentPage, hasPreviousGroup, hasNextGroup);
        bindEvents(startPage, hasPreviousGroup, hasNextGroup);
    }

    window.renderPagination = renderPagination;
})();
